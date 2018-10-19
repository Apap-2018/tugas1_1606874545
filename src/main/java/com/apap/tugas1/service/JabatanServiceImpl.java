package com.apap.tugas1.service;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.repository.JabatanDB;
import com.apap.tugas1.repository.PegawaiDB;




@Service
@Transactional
public class JabatanServiceImpl implements JabatanService {
	
	@Autowired
	private JabatanDB jabatanDB;
	
	@Autowired
	private PegawaiDB pegawaiDB;
	
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
	
	
/*	public ArrayList<Integer> hitungJumlahPegawai() {
		ArrayList<Integer> jumlahPegawai = new ArrayList<Integer>()
		for(JabatanModel jabatan : jabatanDB.findAll()) {
			int jumlah = jabatan.getPegawaiList().size();
		}
		return null;
	
		
	}*/
	
	
	
	/*public ArrayList<PegawaiModel> getUmur(JabatanModel jabatan) {
		long idTarget = jabatan.getId();
		ArrayList <PegawaiModel> pegawaiInstansi = new ArrayList<PegawaiModel>();
		ArrayList<PegawaiModel> hasil = new ArrayList<PegawaiModel>();
		
		for(PegawaiModel pegawai : pegawaiDB.findAll() ) {
			if(pegawai.getId()==idTarget) {
				pegawaiInstansi.add(pegawai);
			}
		}
		
		int tertua=0;
		int termuda=0;
		
		PegawaiModel pegawaiTertua = new PegawaiModel();
		PegawaiModel pegawaiTermuda = new PegawaiModel();
		
		for(PegawaiModel pegawai : pegawaiInstansi) {
			int umur=hitungUmur(pegawai.getTanggalLahir());
			if(umur>tertua) {
				tertua=umur;
				pegawaiTertua=pegawai;
			}
			if(umur<termuda) {
				termuda=umur;
				pegawaiTermuda=pegawai;
			}
		}
		
		hasil.add(pegawaiTertua);
		hasil.add(pegawaiTertua);
		
		return hasil;
		
	}*/
	
/*	public int hitungUmur(Date tglLahir) {
		
		LocalDate now = LocalDate.now();                          //Today's date
		LocalDate born = LocalDate.of(1960, Month.JANUARY, 1);  //Birth date
		long years = ChronoUnit.YEARS.between(born, now);
		int intYears = (int) years;
		
		return intYears;
	}
	*/

}
