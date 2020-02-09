package com.springweb.wsdl;

import javax.jws.WebService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebService(endpointInterface = "com.springweb.wsdl.WsdlProcess")
public class WsdlProcessImpl implements WsdlProcess {

	final Logger log = LogManager.getLogger();


	@Override
	public WsdlVo process(WsdlVo vo) {

		log.debug("SOAP Service 호출!!!");

		WsdlVo wsdlVo = new WsdlVo();

		if("00".equals(vo.getCode())) {
			wsdlVo.setCode("00");
			wsdlVo.setMessage("webservice 성공!");
		}
		else {
			log.debug(vo.getMessage());

			wsdlVo.setCode("99");
			wsdlVo.setMessage("webservice 실패!");
		}

		return wsdlVo;
	}

}
