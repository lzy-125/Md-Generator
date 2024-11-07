package com.ksyun;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TestResp {
        private String name;
        private int age;
        private String address;


        public List<Teacher> getTeacher() {
                return teacher;
        }

        public void setTeacher(List<Teacher> teacher) {
                this.teacher = teacher;
        }

        private List<Teacher> teacher;

        public Student getStudent() {
                return student;
        }

        public void setStudent(Student student) {
                this.student = student;
        }

        private Student student;

        public void setAddress(String address) {
                this.address = address;
        }

        public void setAge(int age) {
                this.age = age;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getName() {
                return name;
        }

        public int getAge() {
                return age;
        }

        public String getAddress() {
                return address;
        }

        public static class Student {
                private String password;

                private List<String> subList;

                private List<Integer> intList;

                public String getPassword() {
                        return password;
                }

                public void setPassword(String password) {
                        this.password = password;
                }

                public List<String> getSubList() {
                        return subList;
                }

                public void setSubList(List<String> subList) {
                        this.subList = subList;
                }

                public List<Integer> getIntList() {
                        return intList;
                }

                public void setIntList(List<Integer> intList) {
                        this.intList = intList;
                }
        }

        public static class Teacher {

                private String book;

                private Integer age;

                public String getBook() {
                        return book;
                }

                public void setBook(String book) {
                        this.book = book;
                }

                public Integer getAge() {
                        return age;
                }

                public void setAge(Integer age) {
                        this.age = age;
                }
        }
}