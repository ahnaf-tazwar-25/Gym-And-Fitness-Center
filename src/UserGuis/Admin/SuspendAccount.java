/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserGuis.Admin;

/**
 *
 * @author Acer
 */
public class SuspendAccount {
    String name, id, accountType, reason;

    public SuspendAccount(String name, String id, String accountType, String reason) {
        this.name = name;
        this.id = id;
        this.accountType = accountType;
        this.reason = reason;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getReason() {
        return reason;
    }

    
}
