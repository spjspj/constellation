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
package au.gov.asd.tac.constellation.graph.utilities.banner;

import au.gov.asd.tac.constellation.graph.schema.visual.attribute.objects.Banner;
import au.gov.asd.tac.constellation.graph.utilities.widgets.GraphColorChooser;
import au.gov.asd.tac.constellation.utilities.color.ConstellationColor;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SpinnerNumberModel;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.util.NbBundle;
import org.openide.util.NbBundle.Messages;

/**
 * UI panel for the entry of the banner attributes
 *
 * @author algol
 */
@Messages(
        {
            "MSG_fg=Select foreground color",
            "MSG_bg=Select background color"
        })
public class BannerPanel extends javax.swing.JPanel {

    private final Banner defaultBanner = new Banner(true, 0, "User Defined", null, null, "");

    /**
     * Creates new form BannerPanel.
     *
     * @param banner The Banner to be used in the panel.
     */
    public BannerPanel(final Banner banner) {
        initComponents();
        bannerCheck.setSelected(banner.isActive());
        levelSpinner.setValue(banner.getLevel());
        bannerText.setText(banner.getText());
        fgColorPanel.setBackground(banner.getFgColor().getJavaColor());
        bgColorPanel.setBackground(banner.getBgColor().getJavaColor());

        PredefinedBanners banners = PredefinedBanners.getDefault();
        if (banners.getBanners().size() > 0) {
            Banner selectedTemplate = defaultBanner;
            bannerList.addItem(defaultBanner);
            for (int i = 0; i < banners.getBanners().size(); i++) {
                Banner item = banners.getBanners().get(i);
                bannerList.addItem(item);
                if (banner.getTemplate().equalsIgnoreCase(item.getTemplate())) {
                    selectedTemplate = item;
                }
            }
            bannerList.setSelectedItem(selectedTemplate);
            bannerList.setEnabled(true);
        } else {
            bannerList.setEnabled(false);
        }
    }

    public Banner getBanner() {
        final Banner banner;

        final boolean active = bannerCheck.isSelected();
        final SpinnerNumberModel nm = (SpinnerNumberModel) levelSpinner.getModel();
        final int level = nm.getNumber().intValue();
        final ConstellationColor fg = ConstellationColor.fromJavaColor(fgColorPanel.getBackground());
        final ConstellationColor bg = ConstellationColor.fromJavaColor(bgColorPanel.getBackground());
        final Banner selected = (Banner) bannerList.getSelectedItem();
        banner = new Banner(active, level, bannerText.getText(), fg, bg, selected != null ? selected.getTemplate() : null);

        return banner;
    }

    private static Color askForColor(final Color currentColor, final String msg) {
        final GraphColorChooser chooser = new GraphColorChooser();
        chooser.setColor(currentColor);
        final DialogDescriptor dd = new DialogDescriptor(chooser, msg, true, null);
        final Object n = DialogDisplayer.getDefault().notify(dd);
        if (n == NotifyDescriptor.OK_OPTION) {
            return chooser.getColor();
        }

        return null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new JPanel();
        jLabel2 = new JLabel();
        jLabel1 = new JLabel();
        bgColorPanel = new JPanel();
        fgColorPanel = new JPanel();
        jLabel3 = new JLabel();
        bannerText = new JTextField();
        levelSpinner = new JSpinner();
        jLabel4 = new JLabel();
        bannerList = new JComboBox<>();
        jLabel5 = new JLabel();
        jSeparator1 = new JSeparator();
        bannerCheck = new JCheckBox();

        jPanel1.setBorder(BorderFactory.createTitledBorder(NbBundle.getMessage(BannerPanel.class, "BannerPanel.jPanel1.border.title"))); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, NbBundle.getMessage(BannerPanel.class, "BannerPanel.jLabel2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, NbBundle.getMessage(BannerPanel.class, "BannerPanel.jLabel1.text")); // NOI18N

        bgColorPanel.setBackground(new Color(0, 0, 0));
        bgColorPanel.setPreferredSize(new Dimension(32, 16));
        bgColorPanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                bgColorPanelMouseClicked(evt);
            }
        });

        GroupLayout bgColorPanelLayout = new GroupLayout(bgColorPanel);
        bgColorPanel.setLayout(bgColorPanelLayout);
        bgColorPanelLayout.setHorizontalGroup(bgColorPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );
        bgColorPanelLayout.setVerticalGroup(bgColorPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 16, Short.MAX_VALUE)
        );

        fgColorPanel.setBackground(new Color(255, 255, 255));
        fgColorPanel.setPreferredSize(new Dimension(32, 16));
        fgColorPanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                fgColorPanelMouseClicked(evt);
            }
        });

        GroupLayout fgColorPanelLayout = new GroupLayout(fgColorPanel);
        fgColorPanel.setLayout(fgColorPanelLayout);
        fgColorPanelLayout.setHorizontalGroup(fgColorPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );
        fgColorPanelLayout.setVerticalGroup(fgColorPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 16, Short.MAX_VALUE)
        );

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, NbBundle.getMessage(BannerPanel.class, "BannerPanel.jLabel3.text")); // NOI18N

        bannerText.setText(NbBundle.getMessage(BannerPanel.class, "BannerPanel.bannerText.text")); // NOI18N

        levelSpinner.setModel(new SpinnerNumberModel());

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, NbBundle.getMessage(BannerPanel.class, "BannerPanel.jLabel4.text")); // NOI18N

        bannerList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                bannerListActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, NbBundle.getMessage(BannerPanel.class, "BannerPanel.jLabel5.text")); // NOI18N

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(bannerText, GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(fgColorPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(levelSpinner, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 232, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(bgColorPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(bannerList, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jSeparator1, GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(levelSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(bannerText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(fgColorPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, GroupLayout.Alignment.TRAILING)
                    .addComponent(bgColorPanel, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(bannerList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(64, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(106, Short.MAX_VALUE)
                    .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(99, Short.MAX_VALUE)))
        );

        bannerCheck.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(bannerCheck, NbBundle.getMessage(BannerPanel.class, "BannerPanel.bannerCheck.text")); // NOI18N
        bannerCheck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                bannerCheckActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bannerCheck)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bannerCheck)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void fgColorPanelMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_fgColorPanelMouseClicked
    {//GEN-HEADEREND:event_fgColorPanelMouseClicked
        final Color c = askForColor(fgColorPanel.getBackground(), Bundle.MSG_fg());
        if (c != null) {
            fgColorPanel.setBackground(c);
        }
    }//GEN-LAST:event_fgColorPanelMouseClicked

    private void bgColorPanelMouseClicked(MouseEvent evt)//GEN-FIRST:event_bgColorPanelMouseClicked
    {//GEN-HEADEREND:event_bgColorPanelMouseClicked
        final Color c = askForColor(fgColorPanel.getBackground(), Bundle.MSG_bg());
        if (c != null) {
            bgColorPanel.setBackground(c);
        }
    }//GEN-LAST:event_bgColorPanelMouseClicked

    private void bannerCheckActionPerformed(ActionEvent evt)//GEN-FIRST:event_bannerCheckActionPerformed
    {//GEN-HEADEREND:event_bannerCheckActionPerformed
    }//GEN-LAST:event_bannerCheckActionPerformed

    private void bannerListActionPerformed(ActionEvent evt)//GEN-FIRST:event_bannerListActionPerformed
    {//GEN-HEADEREND:event_bannerListActionPerformed
        Banner tmp = (Banner) bannerList.getSelectedItem();
        if (tmp == defaultBanner) {
            levelSpinner.setEnabled(true);
            bannerText.setEnabled(true);
            fgColorPanel.setEnabled(true);
            bgColorPanel.setEnabled(true);
        } else {
            levelSpinner.setEnabled(false);
            bannerText.setEnabled(false);
            fgColorPanel.setEnabled(false);
            bgColorPanel.setEnabled(false);

            levelSpinner.setValue(tmp.getLevel());
            bannerText.setText(tmp.getText());
            fgColorPanel.setBackground(tmp.getFgColor().getJavaColor());
            bgColorPanel.setBackground(tmp.getBgColor().getJavaColor());
        }
    }//GEN-LAST:event_bannerListActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JCheckBox bannerCheck;
    private JComboBox<Banner> bannerList;
    private JTextField bannerText;
    private JPanel bgColorPanel;
    private JPanel fgColorPanel;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JPanel jPanel1;
    private JSeparator jSeparator1;
    private JSpinner levelSpinner;
    // End of variables declaration//GEN-END:variables
}
