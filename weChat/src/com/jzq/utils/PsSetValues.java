package com.jzq.utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PsSetValues {
	public void setValue(PreparedStatement pst) throws SQLException;
}
