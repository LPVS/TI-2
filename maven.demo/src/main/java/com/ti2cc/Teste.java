package com.ti2cc;

public class Teste {
	private int cod1;
	private int cod2;
	
	public Teste() {
		this.cod1 = -1;
		this.cod2 = -1;
	}
	
	public Teste(int cod1, int cod2) {
		this.cod1 = cod1;
		this.cod2 = cod2;
	}

	public int getCod1() {
		return cod1;
	}

	public void setCod1(int cod1) {
		this.cod1 = cod1;
	}

	public int getCod2() {
		return cod2;
	}

	public void setCod2(int cod2) {
		this.cod2 = cod2;
	}

	@Override
	public String toString() {
		return "Usuario [cod1=" + cod1 + ", cod2=" + cod2 + "]";
	}	
}
