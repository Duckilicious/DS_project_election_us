import requests
import numpy

list_of_states = ["new_york", "arizona", "texas"]
list_of_candidates = [10, 20, 30]

for _ in range(20):
  nsn = numpy.random.choice(range(1000000))
  candidate = numpy.random.choice(list_of_candidates)
  state = numpy.random.choice(list_of_states)
  vote_str = "{\r\n        \"national_security_number\": " + str(nsn) + ",\r\n        \"candidate\": " + str(candidate) + ",\r\n        \"state\": " + "\"" + state + "\"" + "\r\n    }"

  url = "http://localhost:9090/votes/"

  #payload = "{\r\n        \"national_security_number\": nsn,\r\n        \"candidate\": candidate,\r\n        \"state\": \"new_york\"\r\n    }"
  headers = {
    'Content-Type': 'application/json'
  }

  response = requests.request("POST", url, headers=headers, data = vote_str)

  print(response.text.encode('utf8'))
