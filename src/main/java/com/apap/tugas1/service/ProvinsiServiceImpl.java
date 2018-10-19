package com.apap.tugas1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.repository.InstansiDB;
import com.apap.tugas1.repository.PegawaiDB;
import com.apap.tugas1.repository.ProvinsiDB;

@Service
@Transactional
public class ProvinsiServiceImpl implements ProvinsiService {
	private PegawaiDB pegawaiDB;
	private InstansiDB instansiDB;
	private PegawaiModel pegawaiModel;
	private InstansiModel instansiModel;
	private ProvinsiModel provinsiModel;
	
	@Autowired
	private ProvinsiDB provinsiDB;
	
	@Override
	public Optional<ProvinsiModel> getProvinsiById(long id) {
		System.out.println("tes");
		System.out.println(id+"BBB");
		System.out.println(provinsiDB.findById(id).get().getNama());
		return provinsiDB.findById(id);
	}
	
}
