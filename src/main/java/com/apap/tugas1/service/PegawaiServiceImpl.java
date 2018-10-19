package com.apap.tugas1.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	public void tambahPegawai(PegawaiModel pegawaiBaru) {
		pegawaiDB.save(pegawaiBaru);
	}
	
	
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
	
	
	
	@Override
	public String buatNip(PegawaiModel pegawai) {
		DateFormat dateFormat = new SimpleDateFormat("ddMMYY");
		Date tglLahir = pegawai.getTanggalLahir();
		String formatted = dateFormat.format(tglLahir);
		System.out.println("date->"+formatted);
		
		Long kodeInstansi = pegawai.getInstansi().getId();
		System.out.println("kode instansi->"+kodeInstansi);
		
		int idAkhir = 0;
		for (PegawaiModel peg : pegawaiDB.findAll()) {
			if (peg.getTanggalLahir().equals(pegawai.getTanggalLahir()) && peg.getTahunMasuk().equals(pegawai.getTahunMasuk())) {
				idAkhir+=1;
			}
		}
		idAkhir+=1;
		
		String kodeMasuk = "";
		if (idAkhir<10) {
			kodeMasuk = "0"+idAkhir;
		}
		else {
			kodeMasuk = Integer.toString(idAkhir);
		}
		
		System.out.println(kodeInstansi+formatted+pegawai.getTahunMasuk()+kodeMasuk);
		return kodeInstansi+formatted+pegawai.getTahunMasuk()+kodeMasuk;
		
	}
	
	
	
	
	
	
	
	
	
	

}
