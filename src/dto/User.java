package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class User implements Serializable {

    private static final long serialVersionUID = 4545864587995944260L;
    private int	userId;
    private String userName;
    private String ini;
    private List<String> roles;

    private String cpr;
    private String password;



    public User(int userId, String userName, String ini, List<String> roles, String cpr, String password) {
        this.userId = userId;
        this.userName = userName;
        this.ini = ini;
        this.roles = roles;
        this.cpr = cpr;
        this.password = password;
    }

    public User(int ingenting){
    }

    Scanner s = new Scanner(System.in);

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getIni() {
        return ini;
    }
    public void setIni(String ini) {
        this.ini = ini;
    }

    public List<String> getRoles() {
        return roles;
    }
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public void addRole(String role){
        this.roles.add(role);
    }

    public String getCpr() {
        return cpr;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    /**
     *
     * @param role
     * @return true if role existed, false if not
     */
    public boolean removeRole(String role){
        return this.roles.remove(role);
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", userName=" + userName + ", ini=" + ini + ", users=" + roles + "]";
    }
}

