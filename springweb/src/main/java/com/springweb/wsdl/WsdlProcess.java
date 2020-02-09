package com.springweb.wsdl;

import javax.jws.WebService;

@WebService
public interface WsdlProcess {

	WsdlVo process(WsdlVo vo);
}
