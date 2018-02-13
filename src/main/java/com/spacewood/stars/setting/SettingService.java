/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacewood.stars.setting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author webdesign
 */
@Service
public class SettingService {
    @Autowired
    private SettingDAL settingDAL;

    public void set(SettingKey key, String value) {
        try {
            //if exists then update existing entry
            Setting setting = settingDAL.findByKey(key);
            setting.setSettingValue(value);
            settingDAL.update(setting);
        } catch (EmptyResultDataAccessException erdae) {
            //if setting does not exist then create new entry
            Setting setting = new Setting();
            setting.setSettingKey(key);
            setting.setSettingValue(value);
            settingDAL.insert(setting);
        }
    }

    public Setting get(SettingKey key) {
        try {
            return settingDAL.findByKey(key);
        } catch (EmptyResultDataAccessException erdae) {
            //if setting with key is not found then return null
            Setting setting = new Setting();
            setting.setSettingKey(key);
            return setting;
        }
    }

}
