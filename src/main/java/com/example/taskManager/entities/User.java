package com.example.taskManager.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "USERS")
public class User {

	/*ATRIBUTOS*/
	
	@Id
    private String name;
    private String password;
    private Boolean enabled = true;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol = Rol.USER;
    
    // Relación con DataUser
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private DataUser dataUser;
    
    // NUEVO: lista de tareas
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();

    public User() {}

    public User(String name, String password, Rol rol) {
        this.name = name;
        this.password = password;
        this.rol = rol;
    }

    // Getters y setters
    public String getUsername() { return name; }
    public void setUsername(String name) { this.name = name; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Boolean getEnabled() { return enabled; }
    public void setEnabled(Boolean enabled) { this.enabled = enabled; }

    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }
    
    public DataUser getDataUser() {
		return dataUser;
	}

    public void setDataUser(DataUser dataUser) {
        this.dataUser = dataUser;
        if (dataUser != null) {
            dataUser.setUser(this); // sincronizamos la relación
        }
    }

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	@Override
    public String toString() {
        return "UserLogin [name=" + name + ", password=" + password +
               ", enabled=" + enabled + ", rol=" + rol + "]";
    }
	
}
