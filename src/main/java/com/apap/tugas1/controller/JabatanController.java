package com.apap.tugas1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.repository.JabatanDB;
import com.apap.tugas1.service.JabatanService;
import com.apap.tugas1.service.PegawaiService;


@Controller
public class JabatanController {
	
@Autowired
private PegawaiService pegawaiService;
@Autowired
private JabatanService jabatanService;
@Autowired
private JabatanDB jabatanDB;


@RequestMapping(value="/jabatan/tambah", method=RequestMethod.GET)
private String tambahJabatan(Model model) {
	model.addAttribute("jabatan", new JabatanModel());
	return "tambahJabatan";
}

@RequestMapping(value="/jabatan/tambah",method=RequestMethod.POST)
private String tambahJabatanSubmit(@ModelAttribute JabatanModel jabatanSubmit, Model model) {
	jabatanService.tambahJabatan(jabatanSubmit);
	model.addAttribute("jabatan", new JabatanModel());
	return "tambahJabatan";
	
}

@RequestMapping("/jabatan/viewall")
private String viewAllJabatan(Model model) {
	
	model.addAttribute("jabatan", jabatanDB.findAll());
	return "viewAllJabatan";
}

@RequestMapping(value= {"/jabatan/view"}, method=RequestMethod.GET)
private String viewJabatan(String idJabatan, Model model)	{
	long idLong = Long.parseLong(idJabatan);
	JabatanModel jabatan= jabatanService.getJabatanById(idLong).get();
	model.addAttribute("jabatan",jabatan);
	return "viewJabatan";
}

@RequestMapping(value= {"/jabatan/ubah"}, method=RequestMethod.GET)
private String ubahJabatan(String idJabatan, Model model)	{
	long idLong = Long.parseLong(idJabatan);
	JabatanModel jabatan= jabatanService.getJabatanById(idLong).get();
	model.addAttribute("jabatan",jabatan);
	return "updateJabatan";
}

@RequestMapping(value="/jabatan/ubah",method=RequestMethod.POST)
private String ubahJabatanSubmit(@ModelAttribute JabatanModel dataBaru, Model model) {
	long idJabatan = dataBaru.getId();
	JabatanModel jabatan= jabatanService.getJabatanById(idJabatan).get();
	jabatanService.ubahJabatan(dataBaru, idJabatan);
	model.addAttribute("jabatan",jabatan);
	return "updateJabatan";
	
}

@RequestMapping(value="/jabatan/hapus",method=RequestMethod.POST)
private String hapusJabatan(@ModelAttribute JabatanModel jabatanHapus, Model model) {
	
	long id = jabatanHapus.getId();
	JabatanModel jabatan = jabatanService.getJabatanById(id).get();
	jabatanService.hapusJabatan(jabatan);
	return "redirect:/";
}




}
