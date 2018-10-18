package com.apap.tugas1.service;

import java.util.Optional;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.PegawaiModel;



public interface PegawaiService {

	PegawaiModel getPegawaiDetailByNip(String nip);

	double getGajiPegawai(String nip);

	
	
}
