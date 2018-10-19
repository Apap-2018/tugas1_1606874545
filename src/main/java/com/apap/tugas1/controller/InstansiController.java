package com.apap.tugas1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.repository.InstansiDB;
import com.apap.tugas1.repository.JabatanDB;
import com.apap.tugas1.service.InstansiService;
import com.apap.tugas1.service.JabatanService;
import com.apap.tugas1.service.PegawaiService;

@Controller
public class InstansiController {
	
	@Autowired
	private PegawaiService pegawaiService;
	@Autowired
	private JabatanService jabatanService;
	@Autowired
	private JabatanDB jabatanDB;
	@Autowired
	private InstansiService instansiService;
	@Autowired
	private InstansiDB instansiDB;
	
	
	@RequestMapping(value= {"/pegawai/termuda-tertua"}, method=RequestMethod.GET)
	private String pegawaiTermudaTertua(String idInstansi, Model model)	{
		long idLong = Long.parseLong(idInstansi);
		InstansiModel instansi = instansiDB.findById(idLong).get();
		List<PegawaiModel> pegawai = instansiService.getPegawaiTuaMuda(instansi);
		PegawaiModel pegawaiTertua = pegawai.get(0);
		PegawaiModel pegawaiTermuda = pegawai.get(pegawai.size()-1);
		
		model.addAttribute("pegawaiTermuda", pegawaiTermuda);
		model.addAttribute("pegawaiTertua", pegawaiTertua);
		
		return "pegawaiTermudaTertua";
	}

}
