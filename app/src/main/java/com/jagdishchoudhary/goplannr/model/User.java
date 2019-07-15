package com.jagdishchoudhary.goplannr.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "user_table")

public class User implements Serializable {

        @PrimaryKey(autoGenerate = true)
        private int uid;
        @NonNull
        @ColumnInfo(name = "name")
        private String name;

        @ColumnInfo(name = "phone")
        private String phone;

        @ColumnInfo(name = "age")
        private Integer age;

        @ColumnInfo(name = "salary")
        private Integer salary;


        public User(@NonNull String name, String phone, Integer age, Integer salary) {
                this.name = name;
                this.phone = phone;
                this.age = age;
                this.salary = salary;
        }


        public String getName(){return this.name;}
        public String getPhone(){return this.phone;}
        public Integer getAge(){return this.age;}
        public Integer getSalary(){return this.salary;}

}
