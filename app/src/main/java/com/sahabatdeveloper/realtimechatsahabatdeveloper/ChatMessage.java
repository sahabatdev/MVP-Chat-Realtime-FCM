package com.sahabatdeveloper.realtimechatsahabatdeveloper;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChatMessage{
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("dari")
        @Expose
        private String dari;
        @SerializedName("ke")
        @Expose
        private String ke;
        @SerializedName("pesan")
        @Expose
        private String pesan;

        public ChatMessage(Integer id, String dari, String ke, String pesan) {
            this.id = id;
            this.dari = dari;
            this.ke = ke;
            this.pesan = pesan;
        }



    public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getDari() {
            return dari;
        }

        public void setDari(String dari) {
            this.dari = dari;
        }

        public String getKe() {
            return ke;
        }

        public void setKe(String ke) {
            this.ke = ke;
        }

        public String getPesan() {
            return pesan;
        }

        public void setPesan(String pesan) {
            this.pesan = pesan;
        }
    }