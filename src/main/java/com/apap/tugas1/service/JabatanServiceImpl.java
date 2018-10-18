package com.apap.tugas1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.repository.JabatanDB;


@Service
@Transactional
public class JabatanServiceImpl implements JabatanService {
	
	@Autowired
	private JabatanDB jabatanDB;
	
	@Override
	public void tambahJabatan(JabatanModel jabatanBaru) {
		jabatanDB.save(jabatanBaru);
	}
	
	@Override
	public Optional<JabatanModel> getJabatanById(long id) {
		return jabatanDB.findById(id);
	}
	
	@Override
	public void ubahJabatan(JabatanModel dataBaru, Long id) {
		JabatanModel jabatanTarget = jabatanDB.findById(id).get();
		System.out.println("masuk service");
		jabatanTarget.setNama(dataBaru.getNama());
		jabatanTarget.setDeskripsi(dataBaru.getDeskripsi());
		jabatanTarget.setGajiPokok(dataBaru.getGajiPokok());
		jabatanDB.save(jabatanTarget);
	}
	
	@Override
	public void hapusJabatan(JabatanModel jabatanHapus) {
		
		jabatanDB.delete(jabatanHapus);
		
	}

}
