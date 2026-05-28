package com.fileHandling.project.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Dimensions {

	private double width;
	private double height;
	private double depth;
	
}
