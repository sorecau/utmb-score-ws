Here’s a clean and professional `README.md` based on your description:

---

# UTMB Score Web Service (`utmb-score-ws`)

A Java Spring Boot web service for retrieving runners' scores from [utmb.world](https://utmb.world).
It searches runners based on their names and applies a name-matching algorithm with a minimum similarity threshold of **80%** to ensure accurate identification.

---

## 🔍 Matching Algorithm

The algorithm:

* Ignores word order (e.g., `"Jim Walmsley"` = `"Walmsley Jim"`).
* Normalizes casing and diacritics.
* Uses fuzzy matching with at least **80% similarity** for name equivalence.
* Optionally improves accuracy by including the runner's nationality.

### ✅ Match Examples:

| Input Name      | UTMB Result      | Match? |
| --------------- | ---------------- | ------ |
| Jim Walmsley    | Walmsley Jim     | ✅ Yes  |
| Jimmy Walmsley  | Walmsley Jim     | ❌ No   |
| Stefan Kernstok | Stefan Kernstock | ✅ Yes  |
| Toni Mcann      | Toni Mccann      | ✅ Yes  |
| Toni Mcan       | Toni Mccann      | ❌ No   |

---

## 📥 Request Format

The API consumes a JSON array of runner requests:

```json
[
  {
    "name": "Kilian Jornet Burgada",
    "nationality": "ES"
  },
  {
    "name": "Jim Walmsley",
    "nationality": "US"
  }
]
```

### Fields:

* `name` *(required)* – Full name of the runner.
* `nationality` *(optional)* – 2-letter ISO country code for better accuracy.

---

## 📤 Response Format

The API responds with a JSON array containing matched runners and their scores:

```json
[
  {
    "ip": 945,
    "fullname": "Jim WALMSLEY",
    "nationality": "US",
    "sex": "H"
  },
  {
    "ip": 941,
    "fullname": "Kilian JORNET BURGADA",
    "nationality": "ES",
    "sex": "H"
  }
]
```

### Response Fields:

* `ip` – UTMB score (higher is better).
* `fullname` – Official runner name from UTMB.
* `nationality` – Runner's nationality.
* `sex` – Gender (`H` = Male, `F` = Female).

---

## 🛠 Tech Stack

* Java 17+
* Spring Boot
* Apache Commons Text (for fuzzy string matching)

---

## 📦 How to Run

```bash
mvn clean spring-boot:run
```

---

Let me know if you’d like a Swagger/OpenAPI section or Docker instructions added.
