package com.dao.wj.musicdao;

import java.util.List;

import com.dao.wj.commondao.GenericCommonDaoImpl;
import com.entity.wj.music.Music;
/**
 * 音乐排行榜
 * @author 汪进
 * 点击量，口碑
 */
public class MusicRankingDao extends GenericCommonDaoImpl<Music, Integer> {
    //按Count排序
	@Override
	public List<Object[]> getRankingByCountEntity(Music entity) {
		// TODO Auto-generated method stub
		return super.getRankingByCountEntity(entity);
	}
	//按Score排序
	@Override
	public List<Object[]> getRankingByScoreEntity(Music entity) {
		// TODO Auto-generated method stub
		return super.getRankingByScoreEntity(entity);
	}
		
}
