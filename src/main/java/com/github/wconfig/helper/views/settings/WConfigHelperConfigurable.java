package com.github.wconfig.helper.views.settings;

import com.github.wconfig.helper.Settings;
import com.github.wconfig.helper.component.CommonComponent;
import com.github.wconfig.helper.localstorage.LocalStorage;
import com.intellij.openapi.options.SearchableConfigurable;
import org.apache.commons.collections.CollectionUtils;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * WConfigHelperConfigurable
 * setting 窗口中的配置界面
 *
 * @author: lupeng10
 * @create: 2023-05-24 11:31
 */
public class WConfigHelperConfigurable implements SearchableConfigurable {

    private final SettingsPanelView panelView;

    public WConfigHelperConfigurable() {
        Settings settings = LocalStorage.getSetting();
        this.panelView = new SettingsPanelView(settings.getCookie(), settings.getOaName());

        this.panelView.setEnableDefaultGroup(settings.isEnableDefaultGroup());
        this.panelView.setGroup(settings.getDefaultGroup());

        this.panelView.setEnableGray(settings.isEnableGray());
        this.panelView.setGrayIp(settings.getGrayIp());

        this.panelView.setSearchKeys(settings.getSearchKeys());
        if (CollectionUtils.isNotEmpty(CommonComponent.allGroup)) {
            this.panelView.initGroup(CommonComponent.allGroup);
        }
    }

    @Override
    public @NotNull @NonNls String getId() {
        return "com.bj58.plugin.wconfig-helper";
    }

    @Override
    public String getDisplayName() {
        return "WConfigHelper";
    }

    @Override
    public @Nullable JComponent createComponent() {
        return panelView.getMainComponent();
    }

    @Override
    public boolean isModified() {
        return panelView.isModified();
    }

    @Override
    public void reset() {
        panelView.reset();
    }

    @Override
    public void apply() {
        Settings settings = LocalStorage.getSetting();
        settings.setCookie(panelView.getCookiesValue());
        settings.setOaName(panelView.getOaNameValue());

        settings.setEnableDefaultGroup(panelView.isEnableDefaultGroup());
        settings.setDefaultGroup(panelView.getGroup());

        settings.setEnableGray(panelView.isEnableGray());
        settings.setGrayIp(panelView.getGrayIp());

        settings.setSearchKeys(panelView.getSearchKeys());
    }
}
