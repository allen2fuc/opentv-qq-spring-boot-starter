package cn.fc.opentv.service;

import cn.fc.opentv.domain.dto.ConfirmOrderDTO;
import cn.fc.opentv.domain.dto.CreateOrderDTO;
import cn.fc.opentv.domain.dto.GetVuidDTO;
import cn.fc.opentv.domain.dto.VipInfoDTO;
import cn.fc.opentv.domain.vo.ConfirmOrderVO;
import cn.fc.opentv.domain.vo.CreateOrderVO;
import cn.fc.opentv.domain.vo.GetTokenVO;
import cn.fc.opentv.domain.vo.GetVuidVO;
import cn.fc.opentv.domain.vo.VipInfoVO;

public interface IOpenTvOperateService {
	GetVuidVO getVuid(GetVuidDTO getVuidDTO);

	CreateOrderVO createOrder(CreateOrderDTO createOrderDTO);

	ConfirmOrderVO confirmOrder(ConfirmOrderDTO confirmOrderDTO);

	VipInfoVO vipInfo(VipInfoDTO vipInfoDTO);

	GetTokenVO getToken();

	void refreshToken();
}