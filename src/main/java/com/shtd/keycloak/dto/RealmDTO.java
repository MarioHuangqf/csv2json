package com.shtd.keycloak.dto;

import java.util.List;

public class RealmDTO {
    public String reaml;
    public List users;

    public String getReaml() {
        return reaml;
    }

    public void setReaml(String reaml) {
        this.reaml = reaml;
    }

    public List getUsers() {
        return users;
    }

    public void setUsers(List users) {
        this.users = users;
    }
}
