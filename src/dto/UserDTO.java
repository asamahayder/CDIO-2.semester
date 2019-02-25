package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class UserDTO implements Serializable {

    private static final long serialVersionUID = 4545864587995944260L;
    private int	userId;
    private String userName;
    private String ini;
    private List<String> roles;

    private String cpr;
    private String password;
    //TODO Add relevant fields
    Scanner s = new Scanner(System.in);

    public int getUserId() {
        System.out.println("Indtast userid");
        userId = s.nextInt();
        s.nextLine();
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getUserName() {
        System.out.println("Indtast username");
        userName = s.nextLine();
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getIni() {
        System.out.println("Indtast ini");
        ini = s.nextLine();
        return ini;
    }
    public void setIni(String ini) {
        this.ini = ini;
    }

    public List<String> getRoles() {
        System.out.println("Indtast roles");
        roles = Collections.singletonList(s.nextLine());

        return roles;
    }
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public void addRole(String role){
        this.roles.add(role);
    }

    public String getCpr() {
        System.out.println("Indtast cpr");
        cpr = s.nextLine();
        return cpr;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }

    public String getPassword() {
        System.out.println("Indtast password");
        s.nextLine();
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
        return "UserDTO [userId=" + userId + ", userName=" + userName + ", ini=" + ini + ", roles=" + roles + "]";
    }
}

