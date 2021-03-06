package ar.edu.unlam.analisisSoftware.ehims.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ClaseJava {

	
	
	private File claseJava;
	private Long cantidadDeLineas;
	private Long cantidadLineasComentadas;
	private Long cantidadLineasEnBlanco;
	
	

	public ClaseJava(File archivoJava){
		init();
		this.setClaseJava(archivoJava);
	}
	
	private void init() {
		this.cantidadDeLineas=0L;
		this.cantidadLineasComentadas=0L;
		this.cantidadLineasEnBlanco=0L;
	}
	
	public ClaseJava(){
		init();
	}



	private void ejecutarContabilizacion() {
		FileReader fr = null;
		BufferedReader br = null;
		try{
			fr = new FileReader(this.claseJava);
			br= new BufferedReader(fr);
			sacarPorcentajeComentarios(br);
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		
	}


	public File getClaseJava() {
		return claseJava;
	}

	
	public void setClaseJava(File claseJava) {
		this.claseJava = claseJava;
		ejecutarContabilizacion();
	}


	public Long getCantidadDeLineas() {
		return cantidadDeLineas;
	}


	public Long getCantidadLineasComentadas() {
		return cantidadLineasComentadas;
	}

	public Long getCantidadLineasEnBlanco() {
		return cantidadLineasEnBlanco;
	}
	
	public Long getCantidadLineasDeCodigo(){
		return this.cantidadDeLineas-this.cantidadLineasComentadas-this.cantidadLineasEnBlanco;
	}
	
	
	private void sacarPorcentajeComentarios(BufferedReader br) {
		String linea;
		Boolean comentarioMultilinea = false;
		try {
//			Leo la linea
			linea = br.readLine();
//			Mientras la linea no sea null, significa que hay una linea para leer
			while(linea != null){
				linea= linea.trim();
				if(linea.startsWith("//") && !comentarioMultilinea ){
					cantidadLineasComentadas++;
				}
				//Si empieza con el comentario mutilinea tengo que colocarlo en true
				if(linea.startsWith("/*")){
					this.cantidadLineasComentadas ++;
					comentarioMultilinea = true;
				}
				
				if(linea.isEmpty()){
					cantidadLineasEnBlanco++;
				}
				this.cantidadDeLineas++;
				linea = br.readLine();
			}
		} catch (IOException e) {
			//TODO: pordria agregar una exception propia para ver lo que falla. Es algo a mejorar.
			e.printStackTrace();
		}
		
	}
	
}
