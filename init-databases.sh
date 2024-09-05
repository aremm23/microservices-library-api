#!/bin/bash
set -e

psql -U postgres -c "CREATE DATABASE authentication_db;"
psql -U postgres -c "CREATE DATABASE core_book_db;"
psql -U postgres -c "CREATE DATABASE library_db;"
