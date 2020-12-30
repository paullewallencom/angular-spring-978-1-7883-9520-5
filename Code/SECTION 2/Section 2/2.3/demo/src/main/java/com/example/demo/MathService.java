package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class MathService {

	public int sum(int a, int b) {
		return a + b;
	}

	public int sub(int a, int b) {
		return a - b;
	}

	public int mul(int a, int b) {
		return a * b;
	}

}
