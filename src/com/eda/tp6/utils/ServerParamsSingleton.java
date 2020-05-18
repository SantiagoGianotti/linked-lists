package com.eda.tp6.utils;

import java.util.Random;

public class ServerParamsSingleton {
	private static final ServerParamsSingleton INSTANCE = new ServerParamsSingleton();
	private ServerParamsSingleton() {}
	private int probIngreso = 100;
	private int probRegistro = 10;
	private Random rand = new Random();

    public static ServerParamsSingleton getInstance() {
        return INSTANCE;
    }

    public boolean probabilidadIngresa()
	{
		return rand.nextInt(probIngreso) == 1;
	}

	public boolean probabilidadUsuarioRegistrado()
	{
		return rand.nextInt(probRegistro) == 1;
	}

	public void setProbIngreso(int probIngreso) {
		this.probIngreso = probIngreso;
	}

	public void setProbRegistro(int probRegistro) {
		this.probRegistro = probRegistro;
	}
}
