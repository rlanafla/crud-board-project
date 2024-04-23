package com.example.spring_project1.community.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.boot.autoconfigure.domain.EntityScan;
@Entity
public class Board {
    @Id
    @GeneratedValue
}