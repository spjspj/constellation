/*
 * Copyright 2010-2019 Australian Signals Directorate
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package au.gov.asd.tac.constellation.views;

import au.gov.asd.tac.constellation.plugins.logging.ConstellationLogger;
import org.openide.util.HelpCtx;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 * A generic top component.
 *
 * @param <P> The class used by this {@link TopComponent} to display content.
 *
 * @author cygnus_x-1
 */
public abstract class AbstractTopComponent<P> extends TopComponent {

    protected P content;

    /**
     * Builds and initialises the content for this top component. You should
     * call this method in the constructor of your TopComponent implementation
     * after calling the initComponents() method.
     */
    protected abstract void initContent();

    /**
     * This is where you pass in content which will be rendered within the
     * AbstractTopComponent.
     *
     * @return
     */
    protected abstract P createContent();

    /**
     * Returns the content given to this AbstractTopComponent.
     *
     * @return
     */
    public P getContent() {
        return content;
    }

    @Override
    protected void componentOpened() {
        super.componentOpened();
        ConstellationLogger.getDefault().viewStarted(this);
    }

    @Override
    protected void componentClosed() {
        super.componentClosed();
        ConstellationLogger.getDefault().viewStopped(this);
    }

    @Override
    protected void componentShowing() {
        super.componentShowing();
        if (WindowManager.getDefault().isTopComponentFloating(this)) {
            ConstellationLogger.getDefault().viewInfo(this, "Showing / Floating");
        } else if (WindowManager.getDefault().isTopComponentMinimized(this)) {
            ConstellationLogger.getDefault().viewInfo(this, "Showing / Minimised");
        } else {
            ConstellationLogger.getDefault().viewInfo(this, "Showing / Docked");
        }
    }

    @Override
    protected void componentHidden() {
        super.componentHidden();
        if (WindowManager.getDefault().isTopComponentFloating(this)) {
            ConstellationLogger.getDefault().viewInfo(this, "Hidden / Floating");
        } else if (WindowManager.getDefault().isTopComponentMinimized(this)) {
            ConstellationLogger.getDefault().viewInfo(this, "Hidden / Minimised");
        } else {
            ConstellationLogger.getDefault().viewInfo(this, "Hidden / Docked");
        }
    }

    @Override
    protected void componentActivated() {
        super.componentActivated();
        if (WindowManager.getDefault().isTopComponentFloating(this)) {
            ConstellationLogger.getDefault().viewInfo(this, "Activated / Floating");
        } else if (WindowManager.getDefault().isTopComponentMinimized(this)) {
            ConstellationLogger.getDefault().viewInfo(this, "Activated / Minimised");
        } else {
            ConstellationLogger.getDefault().viewInfo(this, "Activated / Docked");
        }
    }

    @Override
    protected void componentDeactivated() {
        super.componentDeactivated();
        if (WindowManager.getDefault().isTopComponentFloating(this)) {
            ConstellationLogger.getDefault().viewInfo(this, "Deactivated / Floating");
        } else if (WindowManager.getDefault().isTopComponentMinimized(this)) {
            ConstellationLogger.getDefault().viewInfo(this, "Deactivated / Minimised");
        } else {
            ConstellationLogger.getDefault().viewInfo(this, "Deactivated / Docked");
        }
    }

    @Override
    public final HelpCtx getHelpCtx() {
        return new HelpCtx(getClass().getName());
    }
}
