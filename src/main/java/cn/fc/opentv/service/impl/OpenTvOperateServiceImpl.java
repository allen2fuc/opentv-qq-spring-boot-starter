package cn.fc.opentv.service.impl;

import cn.fc.opentv.config.OpenTvProperties;
import cn.fc.opentv.domain.dto.ConfirmOrderDTO;
import cn.fc.opentv.domain.dto.CreateOrderDTO;
import cn.fc.opentv.domain.dto.GetTokenDTO;
import cn.fc.opentv.domain.dto.GetVuidDTO;
import cn.fc.opentv.domain.dto.VipInfoDTO;
import cn.fc.opentv.domain.vo.ConfirmOrderVO;
import cn.fc.opentv.domain.vo.CreateOrderVO;
import cn.fc.opentv.domain.vo.GetTokenVO;
import cn.fc.opentv.domain.vo.GetVuidVO;
import cn.fc.opentv.domain.vo.VipInfoVO;
import cn.fc.opentv.enums.OpenTvApiEnum;
import cn.fc.opentv.service.AbstractOpenTvService;
import cn.fc.opentv.service.IOpenTvOperateService;
import cn.fc.opentv.utils.IResultAssertValidator;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


public class OpenTvOperateServiceImpl extends AbstractOpenTvService implements IOpenTvOperateService {

	public OpenTvOperateServiceImpl(OpenTvProperties openTvProperties, RestTemplate restTemplate, ObjectMapper objectMapper, IResultAssertValidator resultAssertValidator) {
		super(openTvProperties, restTemplate, objectMapper, resultAssertValidator);
	}

	@Override
	public GetVuidVO getVuid(GetVuidDTO getVuidDTO) {
		return callOpenTvInterface(OpenTvApiEnum.GET_VUID, getVuidDTO, GetVuidVO.class);
	}

	@Override
	public CreateOrderVO createOrder(CreateOrderDTO insertOrderDTO) {
		return callOpenTvInterface(OpenTvApiEnum.CREATE_ORDER, insertOrderDTO, CreateOrderVO.class);
	}

	@Override
	public ConfirmOrderVO confirmOrder(ConfirmOrderDTO confirmOrderDTO) {
		return callOpenTvInterface(OpenTvApiEnum.CONFIRM_ORDER, confirmOrderDTO, ConfirmOrderVO.class);
	}

	@Override
	public VipInfoVO vipInfo(VipInfoDTO vipInfoDTO) {
		return callOpenTvInterface(OpenTvApiEnum.VIP_INFO, vipInfoDTO, VipInfoVO.class);
	}

	@Override
	public GetTokenVO getToken() {
		return callOpenTvInterface(OpenTvApiEnum.GET_TOKEN, new GetTokenDTO(), GetTokenVO.class);
	}
}