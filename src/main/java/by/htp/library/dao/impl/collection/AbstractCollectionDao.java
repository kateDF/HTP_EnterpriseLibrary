package by.htp.library.dao.impl.collection;

import java.util.List;

import by.htp.library.entity.DbEntity;

public class AbstractCollectionDao {

	protected CollectionData data = CollectionData.getInstance();

	protected <T extends DbEntity> int extractNextId(List<T> list) {
		int maxId = 0;
		for (DbEntity entity : list) {
			if (maxId < entity.getId()) {
				maxId = entity.getId();
			}
		}
		return maxId + 1;
	}

}
