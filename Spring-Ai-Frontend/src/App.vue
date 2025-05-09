<template>
  <div class="chat-container">
    <h1 class="chat-title">Spring AI Assistant</h1>

    <div class="chat-card">
      <div class="input-row">
        <input
          v-model="prompt"
          class="chat-input"
          type="text"
          placeholder="Type your prompt..."
        />
        <button @click="askModel" class="chat-button">Ask</button>
      </div>

      <ul v-if="suggestions.length" class="suggestion-box">
        <li
          v-for="(s, index) in suggestions"
          :key="index"
          @click="selectSuggestion(s)"
        >
          {{ s }}
        </li>
      </ul>

      <div
        v-if="renderedResponse"
        class="chat-response"
        v-html="renderedResponse"
      ></div>
    </div>
  </div>
  <Spinner v-if="loading" />
</template>

<script setup>
import { ref, watch } from "vue";
import axios from "axios";
import MarkdownIt from "markdown-it";
import Spinner from "./components/Spinner.vue";
const loading = ref(false);
const prompt = ref("");
const modelResponse = ref("");
const suggestions = ref([]);
const md = new MarkdownIt();
const renderedResponse = ref("");

const askModel = async () => {
  if (!prompt.value.trim()) return;
  suggestions.value = [];
  try {
    loading.value = true;
    const { data } = await axios.get(
      `http://localhost:8080/api/ai/ask?prompt=${prompt.value}`
    );
    modelResponse.value = data;
    renderedResponse.value = md.render(data);
    suggestions.value = [];
  } catch (err) {
    console.error("Error asking model:", err);
  } finally {
    loading.value = false;
  }
};

// Called on each keystroke
const onInput = async () => {
  const lastWord = prompt.value.trim();

  if (!lastWord || lastWord.length < 3) {
    suggestions.value = [];
    return;
  }
  if (suggestions.value.includes(lastWord)) {
    suggestions.value = [];
    return;
  }
  try {
    const { data } = await axios.get(
      `http://localhost:8080/api/ai/search?query=${lastWord}`
    );
    suggestions.value = data;
  } catch (err) {
    suggestions.value = [];
  }
};
watch(prompt, onInput);
const selectSuggestion = (text) => {
  prompt.value = text;
};
</script>
<style scoped>
.chat-container {
  background-image: url("./assets/bgimage.jpg");
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  background-attachment: fixed;
  backdrop-filter: blur(1px);
}
.input-row {
  display: flex;
  width: 100%;
  gap: 10px;
  position: sticky;
  top: 0;
  background-color: #2d2d2d;
  padding-bottom: 10px;
  z-index: 5;
}

.chat-input {
  margin-top: 20px;
  flex: 1;
  padding: 14px;
  border-radius: 8px;
  border: none;
  font-size: 16px;
  background-color: #3c3c3c;
  color: white;
}

.chat-button {
  margin-top: 20px;
  background-color: #10a37f;
  color: white;
  padding: 0 20px;
  font-size: 15px;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  transition: background 0.2s;
  white-space: nowrap;
  height: 48px;
}

.chat-button:hover {
  background-color: #2a82e0;
}

.input-group {
  width: 100%;
  position: relative;
  margin-bottom: 15px;
}
.suggestion-box {
  background-color: #3a3a3a;
  border: 1px solid #555;
  border-radius: 8px;
  list-style: none;
  padding: 0;
  max-height: 200px;
  overflow-y: auto;
  margin-top: 5px;
  z-index: 1;
  width: 100%;
}

.suggestion-box li {
  padding: 12px 16px;
  cursor: pointer;
  border-bottom: 1px solid #444;
  color: #ddd;
}

.suggestion-box li:hover {
  background-color: #525252;
  color: #fff;
}

.chat-container {
  background-color: #1e1e1e;
  color: #ffffff;
  min-height: 100vh;
  padding: 40px 20px;
  font-family: "Segoe UI", sans-serif;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.chat-title {
  font-size: 2rem;
  color: #ffffff;
  margin-bottom: 20px;
  text-align: center;
}

.chat-card {
  background-color: #2d2d2d;
  padding: 30px;
  border-radius: 12px;
  width: 100%;
  max-width: 700px;
  box-shadow: 0 8px 24px rgba(0, 0, 0, 0.4);
  position: relative;
  display: flex;
  flex-direction: column;
}

.chat-input::placeholder {
  color: #888;
}

.chat-response {
  margin-top: 20px;
  padding: 15px;
  background-color: #3d3d3d;
  border-left: 4px solid #10a37f;
  border-radius: 8px;
  white-space: pre-line;
  font-size: 16px;
  color: #eaeaea;
}

/* ----------- Responsive Styling ----------- */
@media screen and (max-width: 600px) {
  .chat-container {
    padding: 20px 10px;
  }

  .chat-title {
    font-size: 1.5rem;
    margin-bottom: 16px;
  }

  .chat-card {
    padding: 20px;
  }

  .chat-input {
    font-size: 14px;
    padding: 12px;
  }

  .chat-button {
    width: 100%;
    font-size: 14px;
    text-align: center;
  }

  .suggestion-box {
    font-size: 14px;
    top: 100%;
  }

  .chat-response {
    font-size: 15px;
  }
}
</style>
