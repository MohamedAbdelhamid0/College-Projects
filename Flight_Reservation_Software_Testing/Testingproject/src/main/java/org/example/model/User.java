package org.example.model;

import java.util.Objects;

public class User {
    private Long id;
    private String name;
    private String email;
    private String Password;
    boolean emailcheck = false;
    boolean passcheck = false;
    public User() {
    }

    public User(Long id, String name, String email, String Password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.Password = Password;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        this.id = id;
    }

    public String getPassword() {
        return Password;
    }
    public void setPassword(String Password) {
        passcheck = false;
        if (Password == null) {
            throw new IllegalArgumentException("Password cannot be null");
        } else if (Password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters");
        }  else if (!Password.matches(".*[a-z].*")) {
            throw new IllegalArgumentException("Password must contain at least one lowercase letter");
        }else if( (!Character.isUpperCase(Password.charAt(0)) || !Password.contains("!"))){
            throw new IllegalArgumentException("Password must begin with uppercase letter and must contain!");
        }
        else {
            this.Password = Password;
            passcheck = true;
        }
    }

    public boolean isPasscheck() {
        return passcheck;
    }

    public String getName() {
        return name;
    }

    public void setName(String name)
    {
        if(name.equals("")){
            throw new IllegalArgumentException("Name should not be empty");
        }else if(name == null){
            throw new IllegalArgumentException("Name should not be null");
        }else if (name.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Name should not contain numbers");
        }
        this.name = name;
    }


    public void setEmail(String email) {
        emailcheck = false;
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (!email.endsWith("@gmail.com")) {
            throw new IllegalArgumentException("Email must end with '@gmail.com'");
        }else{
            this.email = email;
            emailcheck = true;
        }

    }
    public String getEmail() {
        return email;

    }
    public boolean checkEmail() {
        return emailcheck;
    }
    // toString, equals, hashCode
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
