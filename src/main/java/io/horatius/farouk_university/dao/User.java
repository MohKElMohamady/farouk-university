package io.horatius.farouk_university.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

@Table(value = "scholars")
@AllArgsConstructor
@Data
public class Scholar {
    @PrimaryKey
    private String username;
    @CassandraType(type = CassandraType.Name.TEXT ,userTypeName = "first_name")
    private String firstName;
    @CassandraType(type = CassandraType.Name.TEXT ,userTypeName = "last_name")
    private String lastName;
    @CassandraType(type = CassandraType.Name.TEXT ,userTypeName = "hashed_password")
    private String hashedPassword;

    public Scholar(){}
    public Scholar(String username){
        this.username = username;
    }
    public String getName(){
        return this.username;
    }
}
