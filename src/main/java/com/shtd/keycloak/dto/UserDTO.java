package com.shtd.keycloak.dto;

import java.util.List;
import java.util.Map;

public class UserDTO {
    public Long createdTimestamp;
    public String username;
    public Boolean enabled;
    public Boolean totp;
    public String email;
    public Boolean emailVerified;
    public String firstName;
    public String lastName;
    public Map<String, String> attributes;
    public List credentials;
    public List disableableCredentialTypes;
    public List requiredActions;
    public List realmRoles;
    public Map clientRoles;
    public Integer notBefore;
    public List groups;

    public Long getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(Long createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getTotp() {
        return totp;
    }

    public void setTotp(Boolean totp) {
        this.totp = totp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public List getCredentials() {
        return credentials;
    }

    public void setCredentials(List credentials) {
        this.credentials = credentials;
    }

    public List getDisableableCredentialTypes() {
        return disableableCredentialTypes;
    }

    public void setDisableableCredentialTypes(List disableableCredentialTypes) {
        this.disableableCredentialTypes = disableableCredentialTypes;
    }

    public List getRequiredActions() {
        return requiredActions;
    }

    public void setRequiredActions(List requiredActions) {
        this.requiredActions = requiredActions;
    }

    public List getRealmRoles() {
        return realmRoles;
    }

    public void setRealmRoles(List realmRoles) {
        this.realmRoles = realmRoles;
    }

    public Map getClientRoles() {
        return clientRoles;
    }

    public void setClientRoles(Map clientRoles) {
        this.clientRoles = clientRoles;
    }

    public Integer getNotBefore() {
        return notBefore;
    }

    public void setNotBefore(Integer notBefore) {
        this.notBefore = notBefore;
    }

    public List getGroups() {
        return groups;
    }

    public void setGroups(List groups) {
        this.groups = groups;
    }
}
