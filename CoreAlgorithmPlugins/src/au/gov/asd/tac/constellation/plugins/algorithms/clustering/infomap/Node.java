/*
 * Copyright 2010-2020 Australian Signals Directorate
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
package au.gov.asd.tac.constellation.plugins.algorithms.clustering.infomap;

import au.gov.asd.tac.constellation.plugins.algorithms.clustering.infomap.traits.FlowBase;

/**
 * Node
 *
 * @author algol
 */
public class Node extends NodeBase {

    private FlowBase data;

    public Node() {
        super();
    }

    public Node(final String name, final FlowBase data) {
        super(name);
        this.data = data.copy();
    }

    public Node(final FlowBase data) {
        this("", data);
    }

    public FlowBase getData() {
        return data;
    }

    public void setData(final FlowBase data) {
        this.data = data.copy();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
