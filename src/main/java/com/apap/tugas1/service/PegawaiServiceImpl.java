package com.apap.tugas1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tugas1.repository.InstansiDB;
import com.apap.tugas1.repository.PegawaiDB;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.PegawaiModel;

@Service
@Transactional
public class PegawaiServiceImpl implements PegawaiService {
	@Autowired
	private PegawaiDB pegawaiDB;
	private InstansiDB instansiDB;
	private PegawaiModel pegawaiModel;
	private InstansiModel instansiModel;
	
	/*@Override
	public PegawaiModel getPegawaiDetailByNip(String nip){
		System.out.println(nip+"zz");
		System.out.println("masuk service");
		pegawaiDB.findByNip(nip);
		
		for(PegawaiModel pegawai:pegawaiDB.findAll()) {
			System.out.println(pegawai.getNip());
			if(pegawai.getNip().equalsIgnoreCase(nip)) {
				return pegawai;
			}
		}
		return null;
	}*/
	
	
	@Override
	public PegawaiModel getPegawaiDetailByNip(String nip) {
		return pegawaiDB.findByNip(nip);
	}
	
	
	
	
	
	
	

}
