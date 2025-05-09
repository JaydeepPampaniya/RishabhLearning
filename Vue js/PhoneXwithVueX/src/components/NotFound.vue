<template>
  <div
    class="not-found-container d-flex flex-column align-items-center justify-content-center"
  >
    <h1 class="error-code">404</h1>
    <h2 class="error-message">Oops! Page Not Found</h2>
    <p class="error-description">
      The page you are looking for might have been removed, had its name
      changed, or is temporarily unavailable.
    </p>
    <router-link to="/" class="btn btn-primary mt-3">
      Go Back Home
    </router-link>
  </div>
</template>

<style scoped>
.not-found-container {
  height: 100vh;
  text-align: center;
  background: linear-gradient(to right, #1e272e, #485460);
  color: white;
  padding: 20px;
}

.error-code {
  font-size: 8rem;
  font-weight: bold;
  margin: 0;
  text-shadow: 4px 4px 10px rgba(0, 0, 0, 0.5);
}

.error-message {
  font-size: 2rem;
  font-weight: 600;
  margin: 10px 0;
}

.error-description {
  font-size: 1.2rem;
  max-width: 600px;
  margin: 10px auto;
  opacity: 0.8;
}

.btn-primary {
  background-color: #ff4757;
  color: white;
  font-weight: bold;
  padding: 12px 24px;
  border-radius: 30px;
  transition: 0.3s;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3);
}

.btn-primary:hover {
  background-color: #e84118;
}
</style>
