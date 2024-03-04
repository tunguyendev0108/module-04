package com.codegym.model;
import org.springframework.web.multipart.MultipartFile;
public class ComputerForm {
    private int id;
    private String code;
    private String name;
    private String producer;
    private MultipartFile img;

    public ComputerForm() {
    }

    public ComputerForm(int id, String code, String name, String producer, MultipartFile img) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.producer = producer;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public MultipartFile getImg() {
        return img;
    }

    public void setImg(MultipartFile img) {
        this.img = img;
    }
}
