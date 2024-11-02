package com.ksyun;

public class TestResp {
        private String name;
        private int age;
        private String address;

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
}