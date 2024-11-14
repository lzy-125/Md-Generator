package com.ksyun;

import lombok.Builder;

import java.util.List;
import java.util.Map;

@Builder
public class TestResp {
        private String name;
        private int age;
        private String address;

        public Object getObject() {
                return object;
        }

        public void setObject(Object object) {
                this.object = object;
        }

        public Map<String, Object> getMap() {
                return map;
        }

        public void setMap(Map<String, Object> map) {
                this.map = map;
        }

        private Object object;

        private Map<String, Object> map;


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