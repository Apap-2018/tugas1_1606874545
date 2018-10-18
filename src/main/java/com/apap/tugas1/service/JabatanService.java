package com.apap.tugas1.service;

import java.util.Optional;

import com.apap.tugas1.model.JabatanModel;

public interface JabatanService {

	void tambahJabatan(JabatanModel jabatanBaru);





	Optional<JabatanModel> getJabatanById(long id);





	void ubahJabatan(JabatanModel dataBaru, Long id);





	void hapusJabatan(JabatanModel jabatanHapus);

}
