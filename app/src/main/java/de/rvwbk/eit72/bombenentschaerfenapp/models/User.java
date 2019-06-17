package de.rvwbk.eit72.bombenentschaerfenapp.models;

import java.util.Date;

public class User {
  private String firstname;
  private String lastname;
  private int born;
  private Date created;
  private String passwortHash;

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public int getBorn() {
    return born;
  }

  public void setBorn(int born) {
    this.born = born;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public String getPasswortHash() {
    return passwortHash;
  }

  public void setPasswortHash(String passwortHash) {
    this.passwortHash = passwortHash;
  }
}
