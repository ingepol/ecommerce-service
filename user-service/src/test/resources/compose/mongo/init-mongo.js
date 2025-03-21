db.createUser({
  'user': "user",
  'pwd': "userpass",
  'roles': [
    {
      'role': 'dbOwner',
      'db': "userdb"
    }
  ]
});

db.createCollection("users");
