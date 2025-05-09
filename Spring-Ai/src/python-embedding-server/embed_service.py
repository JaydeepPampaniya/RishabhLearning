from sentence_transformers import SentenceTransformer
from flask import Flask, request, jsonify
import numpy as np

app = Flask(__name__)
model = SentenceTransformer('sentence-transformers/all-MiniLM-L12-v2')

@app.route("/embed", methods=["POST"])
def embed():
    data = request.json
    sentences = data.get("inputs", [])
    embeddings = model.encode(sentences, normalize_embeddings=True)
    return jsonify(embeddings.tolist())

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5005)
