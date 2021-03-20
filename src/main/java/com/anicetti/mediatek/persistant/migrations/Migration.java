package com.anicetti.mediatek.persistant.migrations;

import com.anicetti.mediatek.persistant.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public interface Migration {
    public abstract String getName();
    public abstract String[] up();
    public abstract String[] down();
}
