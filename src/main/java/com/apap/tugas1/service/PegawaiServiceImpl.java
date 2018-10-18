package com.apap.tugas1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tugas1.repository.InstansiDB;
import com.apap.tugas1.repository.PegawaiDB;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;

@Service
@Transactional
public class PegawaiServiceImpl implements PegawaiService {
	@Autowired
	private PegawaiDB pegawaiDB;
	private InstansiDB instansiDB;
	private PegawaiModel pegawaiModel;
	private InstansiModel instansiModel;
	
	
	@Override
	public PegawaiModel getPegawaiDetailByNip(String nip) {
		return pegawaiDB.findByNip(nip);
	}
	
	@Override
	public double getGajiPegawai(String nip) {
		PegawaiModel pegawaiTarget = pegawaiDB.findByNip(nip);
		double gajiPokokMax=0;
		
		for (JabatanModel jabatan : pegawaiTarget.getJabatanList() ) {
			if(jabatan.getGajiPokok()>gajiPokokMax) {
				gajiPokokMax=jabatan.getGajiPokok();
			}
		}
		
		Double tunjangan = pegawaiTarget.getInstansi().getProvinsi().getPresentaseTunjangan();
		
		Double totalGaji = gajiPokokMax+((tunjangan/100)*gajiPokokMax);
		
		return totalGaji;
	}
	
	
	
	
	
	
	

}
