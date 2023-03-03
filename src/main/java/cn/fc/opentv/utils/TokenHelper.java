package cn.fc.opentv.utils;

import java.time.Duration;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import cn.fc.opentv.domain.vo.GetTokenVO;
import cn.fc.opentv.service.IOpenTvOperateService;
import cn.hutool.cache.Cache;
import cn.hutool.cache.impl.TimedCache;

import org.springframework.scheduling.annotation.Scheduled;

import static cn.fc.opentv.utils.TokenHelper.Key.EMPTY;

/**
 * @author fucheng
 * @date 2023/3/3
 */
public class TokenHelper {

	private final Cache<Key, GetTokenVO> singleTokenCache;
	private final ReadWriteLock readWriteLock;
	private final IOpenTvOperateService openTvOperateService;

	public TokenHelper(IOpenTvOperateService openTvOperateService, Duration tokenCacheTime) {
		this.openTvOperateService = openTvOperateService;
		singleTokenCache = new TimedCache<>(tokenCacheTime.toMillis());
		readWriteLock = new ReentrantReadWriteLock();
	}

	protected enum Key {
		EMPTY;
	}

	public GetTokenVO getTokenVO() {
		Lock readLock = readWriteLock.readLock();
		GetTokenVO getTokenVO;
		try {
			readLock.lock();
			getTokenVO = singleTokenCache.get(EMPTY);
		}
		finally {
			readLock.unlock();
		}
		return getTokenVO;
	}

	@Scheduled(fixedDelayString = "${open-tv.tokenRefreshInterval}")
	public void refresh() {
		Lock writeLock = readWriteLock.writeLock();
		try {
			writeLock.lock();
			GetTokenVO token = openTvOperateService.getToken();
			singleTokenCache.put(EMPTY, token);
		}
		finally {
			writeLock.unlock();
		}
	}
}
