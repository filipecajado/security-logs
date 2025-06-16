# Curl Login
    
    curl --location 'http://localhost:8245/auth/login' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "email": "filipe@gmail.com",
        "password": "filipe123"
    
    }'

# Curl Register
    
    curl --location 'http://localhost:8245/users/register' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "email": "filipe@gmail.com",
        "password": "filipe123",
        "name": "flipe cajado",
        "cpf": "18898999763",
        "role": "ADMIN"
    
    }
