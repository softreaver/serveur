package com.auditFal.forms;

import java.sql.Connection;
import java.util.ArrayList;

import com.auditFal.beans.Activity;
import com.auditFal.beans.Pair;
import com.auditFal.dao.ActivityDAO;
import com.auditFal.dao.DAOException;
import com.auditFal.dao.DAOUtils;

public class ActivityForm
{
	private ActivityDAO activityDAO;
	private Connection connection;

	public ActivityForm(Connection connection, ActivityDAO activityDAO) throws Exception
	{
		this.activityDAO = activityDAO;
		this.connection = connection;
		this.connection.setAutoCommit(false);
	}

	public ArrayList<Pair<Long>> saveActivities(ArrayList<Activity> activities) throws Exception
	{
		ArrayList<Pair<Long>> autoGeneratedIds = new ArrayList<>();

		try
		{
			for (Activity activity : activities)
			{
				// Check if item already exists on DB
				Activity check = activityDAO.find(connection, activity);
				// if it does
				if (check != null)
				{
					if (check.getId().longValue() != activity.getId().longValue())
						;
					{
						Pair<Long> pair = new Pair<>();
						/* oldId */ pair.value1 = activity.getId();
						/* id */ pair.value2 = check.getId();
						activity.setId(check.getId());
						autoGeneratedIds.add(pair);
					}
					// Update the item
					activityDAO.update(connection, activity);

					// ...otherwise insert a new element
				} else
				{
					Pair<Long> pair = new Pair<>();
					/* oldId */ pair.value1 = activity.getId();
					/* id */ pair.value2 = activityDAO.create(connection, activity);
					autoGeneratedIds.add(pair);
				}
			}

			connection.commit();

		} catch (DAOException e)
		{
			connection.rollback();
			System.out.println(e.getMessage());
			throw new Exception(e);
		} finally
		{
			DAOUtils.closeConnection(connection);
		}

		return autoGeneratedIds;
	}

	public ArrayList<Activity> getActivities() throws Exception
	{

		try
		{
			return activityDAO.getAll(connection);

		} catch (DAOException e)
		{
			System.out.println(e.getMessage());
			throw new Exception(e);
		} finally
		{
			DAOUtils.closeConnection(connection);
		}
	}
}
