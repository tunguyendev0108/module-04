package com.codegym.service;

import com.codegym.model.Computer;

import java.util.List;

public interface IComputerService {
    List<Computer> findAll();
    void save(Computer computer);
    Computer findById(int id);
    void remove(int id);
}
