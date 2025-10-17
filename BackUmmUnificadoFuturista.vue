<template>
  <div :class="[themeClass, 'back-umm-unificado-container']" ref="rootEl">
    
    <!-- Incluir Font Awesome para los iconos -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" />
    
    <!-- Efectos de partículas de fondo -->
    <div class="particles-container">
      <div v-for="i in 50" :key="i" class="particle" :style="getParticleStyle(i)"></div>
    </div>

    <!-- Efecto de ondas de energía -->
    <div class="energy-waves">
      <div class="wave wave-1"></div>
      <div class="wave wave-2"></div>
      <div class="wave wave-3"></div>
    </div>

    <!-- Panel de control holográfico -->
    <div class="holographic-panel">
      <div class="panel-glow"></div>
      <div class="panel-content">
        <h1 class="main-title">
          <span class="title-text">SISTEMA UMM UNIFICADO</span>
          <div class="title-underline"></div>
        </h1>
        <div class="status-indicators">
          <div class="status-item">
            <div class="status-dot active"></div>
            <span>Sistema Online</span>
          </div>
          <div class="status-item">
            <div class="status-dot processing"></div>
            <span>Procesando Datos</span>
          </div>
        </div>
      </div>
    </div>

    <!-- Navegación por Pestañas Futurista -->
    <div class="tabs-container">
      <div class="tabs-wrapper">
        <button 
          v-for="tab in tabs" 
          :key="tab.id"
          :class="['tab-button', { 'active': activeTab === tab.id }]"
          @click="setActiveTab(tab.id)"
          @mouseenter="playHoverSound"
        >
          <div class="tab-icon-container">
            <i :class="tab.icon"></i>
            <div class="icon-glow"></div>
          </div>
          <span class="tab-text">{{ tab.name }}</span>
          <div v-if="tab.badge" class="tab-badge">
            <div class="badge-pulse"></div>
            {{ tab.badge }}
          </div>
          <div class="tab-ripple"></div>
        </button>
      </div>
    </div>

    <!-- Contenido de las Pestañas con Efectos 3D -->
    <div class="tab-content">
      
      <!-- Pestaña Gestiones Comerciales -->
      <div v-if="activeTab === 'gestiones-comerciales'" class="tab-panel">
        <div class="panel-content">
          <div class="panel-header">
            <div class="header-icon">
              <i class="fas fa-briefcase"></i>
              <div class="icon-aura"></div>
            </div>
            <h3 class="panel-title">Gestiones Comerciales</h3>
            <div class="title-accent"></div>
          </div>
          
          <div class="panel-description">
            <p>Centro de control para todas las operaciones comerciales avanzadas</p>
            <div class="description-line"></div>
          </div>

          <div class="feature-grid">
            <div class="feature-card clickable" @click="crearNuevaGestion" @mouseenter="playCardHover">
              <div class="card-glow"></div>
              <div class="card-content">
                <div class="feature-icon">
                  <i class="fas fa-plus"></i>
                  <div class="icon-pulse"></div>
                </div>
                <h4>Crear Nueva Gestión</h4>
                <p>Iniciar proceso comercial avanzado</p>
                <div class="card-ripple"></div>
              </div>
            </div>

            <div class="feature-card" @mouseenter="playCardHover">
              <div class="card-glow"></div>
              <div class="card-content">
                <div class="feature-icon">
                  <i class="fas fa-edit"></i>
                  <div class="icon-pulse"></div>
                </div>
                <h4>Editar Gestión Existente</h4>
                <p>Modificar parámetros comerciales</p>
              </div>
            </div>

            <div class="feature-card" @mouseenter="playCardHover">
              <div class="card-glow"></div>
              <div class="card-content">
                <div class="feature-icon">
                  <i class="fas fa-eye"></i>
                  <div class="icon-pulse"></div>
                </div>
                <h4>Ver Detalles de Gestión</h4>
                <p>Análisis detallado de operaciones</p>
              </div>
            </div>

            <div class="feature-card" @mouseenter="playCardHover">
              <div class="card-glow"></div>
              <div class="card-content">
                <div class="feature-icon">
                  <i class="fas fa-list-alt"></i>
                  <div class="icon-pulse"></div>
                </div>
                <h4>Seleccionar Solicitud</h4>
                <p>Gestión de solicitudes activas</p>
              </div>
            </div>

            <div class="feature-card" @mouseenter="playCardHover">
              <div class="card-glow"></div>
              <div class="card-content">
                <div class="feature-icon">
                  <i class="fas fa-exclamation-triangle"></i>
                  <div class="icon-pulse"></div>
                </div>
                <h4>Seleccionar Motivo</h4>
                <p>Clasificación de motivos comerciales</p>
              </div>
            </div>

            <div class="feature-card" @mouseenter="playCardHover">
              <div class="card-glow"></div>
              <div class="card-content">
                <div class="feature-icon">
                  <i class="fas fa-calendar"></i>
                  <div class="icon-pulse"></div>
                </div>
                <h4>Reagendar con Fecha y Franja</h4>
                <p>Optimización temporal de gestiones</p>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- Pestaña Gestiones CCOT -->
      <div v-if="activeTab === 'gestiones-ccot'" class="tab-panel">
        <div class="panel-content">
          <div class="panel-header">
            <div class="header-icon">
              <i class="fas fa-chart-bar"></i>
              <div class="icon-aura"></div>
            </div>
            <h3 class="panel-title">Gestiones CCOT</h3>
            <div class="title-accent"></div>
          </div>
          
          <div class="panel-description">
            <p>Centro de control de operaciones técnicas avanzadas</p>
            <div class="description-line"></div>
          </div>

          <div class="feature-grid">
            <div class="feature-card" @mouseenter="playCardHover">
              <div class="card-glow"></div>
              <div class="card-content">
                <div class="feature-icon">
                  <i class="fas fa-plus"></i>
                  <div class="icon-pulse"></div>
                </div>
                <h4>Crear Gestión CCOT</h4>
                <p>Iniciar proceso técnico especializado</p>
              </div>
            </div>

            <div class="feature-card" @mouseenter="playCardHover">
              <div class="card-glow"></div>
              <div class="card-content">
                <div class="feature-icon">
                  <i class="fas fa-edit"></i>
                  <div class="icon-pulse"></div>
                </div>
                <h4>Editar Gestión CCOT</h4>
                <p>Modificar parámetros técnicos</p>
              </div>
            </div>

            <div class="feature-card" @mouseenter="playCardHover">
              <div class="card-glow"></div>
              <div class="card-content">
                <div class="feature-icon">
                  <i class="fas fa-eye"></i>
                  <div class="icon-pulse"></div>
                </div>
                <h4>Ver Detalles CCOT</h4>
                <p>Análisis técnico detallado</p>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- Pestaña Gestiones NOC -->
      <div v-if="activeTab === 'gestiones-noc'" class="tab-panel">
        <div class="panel-content">
          <div class="panel-header">
            <div class="header-icon">
              <i class="fas fa-tools"></i>
              <div class="icon-aura"></div>
            </div>
            <h3 class="panel-title">Gestiones NOC</h3>
            <div class="title-accent"></div>
          </div>
          
          <div class="panel-description">
            <p>Centro de operaciones de red y comunicaciones</p>
            <div class="description-line"></div>
          </div>

          <div class="feature-grid">
            <div class="feature-card" @mouseenter="playCardHover">
              <div class="card-glow"></div>
              <div class="card-content">
                <div class="feature-icon">
                  <i class="fas fa-plus"></i>
                  <div class="icon-pulse"></div>
                </div>
                <h4>Crear Gestión NOC</h4>
                <p>Iniciar operación de red</p>
              </div>
            </div>

            <div class="feature-card" @mouseenter="playCardHover">
              <div class="card-glow"></div>
              <div class="card-content">
                <div class="feature-icon">
                  <i class="fas fa-edit"></i>
                  <div class="icon-pulse"></div>
                </div>
                <h4>Editar Gestión NOC</h4>
                <p>Modificar configuración de red</p>
              </div>
            </div>

            <div class="feature-card" @mouseenter="playCardHover">
              <div class="card-glow"></div>
              <div class="card-content">
                <div class="feature-icon">
                  <i class="fas fa-eye"></i>
                  <div class="icon-pulse"></div>
                </div>
                <h4>Ver Detalles NOC</h4>
                <p>Monitoreo de red en tiempo real</p>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- Pestaña Gestiones Cierres -->
      <div v-if="activeTab === 'gestiones-cierres'" class="tab-panel">
        <div class="panel-content">
          <div class="panel-header">
            <div class="header-icon">
              <i class="fas fa-calendar-check"></i>
              <div class="icon-aura"></div>
            </div>
            <h3 class="panel-title">Gestiones Cierres</h3>
            <div class="title-accent"></div>
          </div>
          
          <div class="panel-description">
            <p>Centro de control para operaciones de cierre</p>
            <div class="description-line"></div>
          </div>

          <div class="feature-grid">
            <div class="feature-card" @mouseenter="playCardHover">
              <div class="card-glow"></div>
              <div class="card-content">
                <div class="feature-icon">
                  <i class="fas fa-plus"></i>
                  <div class="icon-pulse"></div>
                </div>
                <h4>Crear Cierre</h4>
                <p>Iniciar proceso de cierre</p>
              </div>
            </div>

            <div class="feature-card" @mouseenter="playCardHover">
              <div class="card-glow"></div>
              <div class="card-content">
                <div class="feature-icon">
                  <i class="fas fa-edit"></i>
                  <div class="icon-pulse"></div>
                </div>
                <h4>Editar Cierre</h4>
                <p>Modificar parámetros de cierre</p>
              </div>
            </div>

            <div class="feature-card" @mouseenter="playCardHover">
              <div class="card-glow"></div>
              <div class="card-content">
                <div class="feature-icon">
                  <i class="fas fa-eye"></i>
                  <div class="icon-pulse"></div>
                </div>
                <h4>Ver Detalles de Cierre</h4>
                <p>Análisis de operaciones de cierre</p>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- Pestaña Gestiones Unidades -->
      <div v-if="activeTab === 'gestiones-unidades'" class="tab-panel">
        <div class="panel-content">
          <div class="panel-header">
            <div class="header-icon">
              <i class="fas fa-building"></i>
              <div class="icon-aura"></div>
            </div>
            <h3 class="panel-title">Gestiones Unidades</h3>
            <div class="title-accent"></div>
          </div>
          
          <div class="panel-description">
            <p>Centro de control para gestión de unidades</p>
            <div class="description-line"></div>
          </div>

          <div class="feature-grid">
            <div class="feature-card" @mouseenter="playCardHover">
              <div class="card-glow"></div>
              <div class="card-content">
                <div class="feature-icon">
                  <i class="fas fa-plus"></i>
                  <div class="icon-pulse"></div>
                </div>
                <h4>Crear Unidad</h4>
                <p>Registrar nueva unidad</p>
              </div>
            </div>

            <div class="feature-card" @mouseenter="playCardHover">
              <div class="card-glow"></div>
              <div class="card-content">
                <div class="feature-icon">
                  <i class="fas fa-edit"></i>
                  <div class="icon-pulse"></div>
                </div>
                <h4>Editar Unidad</h4>
                <p>Modificar datos de unidad</p>
              </div>
            </div>

            <div class="feature-card" @mouseenter="playCardHover">
              <div class="card-glow"></div>
              <div class="card-content">
                <div class="feature-icon">
                  <i class="fas fa-eye"></i>
                  <div class="icon-pulse"></div>
                </div>
                <h4>Ver Detalles de Unidad</h4>
                <p>Información detallada de unidad</p>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- Pestaña Gestiones Sin Agenda -->
      <div v-if="activeTab === 'gestiones-sin-agenda'" class="tab-panel">
        <div class="panel-content">
          <div class="panel-header">
            <div class="header-icon">
              <i class="fas fa-calendar-times"></i>
              <div class="icon-aura"></div>
            </div>
            <h3 class="panel-title">Gestiones Sin Agenda</h3>
            <div class="title-accent"></div>
          </div>
          
          <div class="panel-description">
            <p>Centro de control para gestiones no programadas</p>
            <div class="description-line"></div>
          </div>

          <div class="feature-grid">
            <div class="feature-card" @mouseenter="playCardHover">
              <div class="card-glow"></div>
              <div class="card-content">
                <div class="feature-icon">
                  <i class="fas fa-plus"></i>
                  <div class="icon-pulse"></div>
                </div>
                <h4>Crear Gestión Sin Agenda</h4>
                <p>Iniciar gestión urgente</p>
              </div>
            </div>

            <div class="feature-card" @mouseenter="playCardHover">
              <div class="card-glow"></div>
              <div class="card-content">
                <div class="feature-icon">
                  <i class="fas fa-edit"></i>
                  <div class="icon-pulse"></div>
                </div>
                <h4>Editar Gestión Sin Agenda</h4>
                <p>Modificar gestión urgente</p>
              </div>
            </div>

            <div class="feature-card" @mouseenter="playCardHover">
              <div class="card-glow"></div>
              <div class="card-content">
                <div class="feature-icon">
                  <i class="fas fa-eye"></i>
                  <div class="icon-pulse"></div>
                </div>
                <h4>Ver Detalles Sin Agenda</h4>
                <p>Monitoreo de gestiones urgentes</p>
              </div>
            </div>
          </div>
        </div>
      </div>

    </div>

    <!-- Panel de control flotante -->
    <div class="floating-controls">
      <button class="control-btn" @click="toggleTheme" title="Cambiar Tema">
        <i :class="theme === 'dark' ? 'fas fa-sun' : 'fas fa-moon'"></i>
      </button>
      <button class="control-btn" @click="refreshAllData" :disabled="isRefreshing" title="Actualizar Datos">
        <i :class="isRefreshing ? 'fas fa-spinner fa-spin' : 'fas fa-sync-alt'"></i>
      </button>
      <button class="control-btn" @click="playSystemSound" title="Sonido del Sistema">
        <i class="fas fa-volume-up"></i>
      </button>
    </div>

  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

// Estado del tema
const theme = ref(localStorage.getItem('theme') || 'dark')
const themeClass = computed(() => `theme-${theme.value}`)

// Estado de la pestaña activa
const activeTab = ref('gestiones-comerciales')

// Estado de refresh
const isRefreshing = ref(false)

// Referencia al elemento raíz
const rootEl = ref(null)

// Configuración de las pestañas de gestión
const tabs = ref([
  {
    id: 'gestiones-comerciales',
    name: 'Gestiones Comerciales',
    icon: 'fas fa-briefcase',
    badge: 'Activo'
  },
  {
    id: 'gestiones-ccot',
    name: 'Gestiones CCOT',
    icon: 'fas fa-chart-bar',
    badge: null
  },
  {
    id: 'gestiones-noc',
    name: 'Gestiones NOC',
    icon: 'fas fa-tools',
    badge: null
  },
  {
    id: 'gestiones-cierres',
    name: 'Gestiones Cierres',
    icon: 'fas fa-calendar-check',
    badge: null
  },
  {
    id: 'gestiones-unidades',
    name: 'Gestiones Unidades',
    icon: 'fas fa-building',
    badge: null
  },
  {
    id: 'gestiones-sin-agenda',
    name: 'Gestiones Sin Agenda',
    icon: 'fas fa-calendar-times',
    badge: null
  }
])

// Funciones de efectos de sonido
const playHoverSound = () => {
  // Crear un sonido de hover sintético
  const audioContext = new (window.AudioContext || window.webkitAudioContext)()
  const oscillator = audioContext.createOscillator()
  const gainNode = audioContext.createGain()
  
  oscillator.connect(gainNode)
  gainNode.connect(audioContext.destination)
  
  oscillator.frequency.setValueAtTime(800, audioContext.currentTime)
  oscillator.frequency.exponentialRampToValueAtTime(1200, audioContext.currentTime + 0.1)
  
  gainNode.gain.setValueAtTime(0.1, audioContext.currentTime)
  gainNode.gain.exponentialRampToValueAtTime(0.01, audioContext.currentTime + 0.1)
  
  oscillator.start(audioContext.currentTime)
  oscillator.stop(audioContext.currentTime + 0.1)
}

const playCardHover = () => {
  const audioContext = new (window.AudioContext || window.webkitAudioContext)()
  const oscillator = audioContext.createOscillator()
  const gainNode = audioContext.createGain()
  
  oscillator.connect(gainNode)
  gainNode.connect(audioContext.destination)
  
  oscillator.frequency.setValueAtTime(600, audioContext.currentTime)
  oscillator.frequency.exponentialRampToValueAtTime(1000, audioContext.currentTime + 0.15)
  
  gainNode.gain.setValueAtTime(0.05, audioContext.currentTime)
  gainNode.gain.exponentialRampToValueAtTime(0.01, audioContext.currentTime + 0.15)
  
  oscillator.start(audioContext.currentTime)
  oscillator.stop(audioContext.currentTime + 0.15)
}

const playSystemSound = () => {
  const audioContext = new (window.AudioContext || window.webkitAudioContext)()
  const oscillator = audioContext.createOscillator()
  const gainNode = audioContext.createGain()
  
  oscillator.connect(gainNode)
  gainNode.connect(audioContext.destination)
  
  oscillator.frequency.setValueAtTime(400, audioContext.currentTime)
  oscillator.frequency.exponentialRampToValueAtTime(800, audioContext.currentTime + 0.2)
  oscillator.frequency.exponentialRampToValueAtTime(1200, audioContext.currentTime + 0.4)
  
  gainNode.gain.setValueAtTime(0.1, audioContext.currentTime)
  gainNode.gain.exponentialRampToValueAtTime(0.01, audioContext.currentTime + 0.4)
  
  oscillator.start(audioContext.currentTime)
  oscillator.stop(audioContext.currentTime + 0.4)
}

// Función para generar estilos de partículas
const getParticleStyle = (index) => {
  const size = Math.random() * 4 + 1
  const left = Math.random() * 100
  const animationDelay = Math.random() * 20
  const animationDuration = Math.random() * 10 + 10
  
  return {
    width: `${size}px`,
    height: `${size}px`,
    left: `${left}%`,
    animationDelay: `${animationDelay}s`,
    animationDuration: `${animationDuration}s`
  }
}

// Funciones principales
const setActiveTab = (tabId) => {
  activeTab.value = tabId
  playSystemSound()
  console.log('Cambiando a pestaña:', tabId)
}

const crearNuevaGestion = () => {
  playSystemSound()
  router.push('/dashboard/back-umm/crear-gestion-comercial')
}

const toggleTheme = () => {
  theme.value = theme.value === 'dark' ? 'light' : 'dark'
  localStorage.setItem('theme', theme.value)
  playSystemSound()
}

const refreshAllData = async () => {
  isRefreshing.value = true
  playSystemSound()
  try {
    // Simular refresh con efectos visuales
    await new Promise(resolve => setTimeout(resolve, 3000))
    console.log('Datos actualizados con efectos futuristas')
  } catch (error) {
    console.error('Error al actualizar datos:', error)
  } finally {
    isRefreshing.value = false
  }
}

// Inicialización
onMounted(() => {
  console.log('Sistema UMM Unificado Futurista montado')
  playSystemSound()
})
</script>

<style scoped>
/* Variables CSS para temas futuristas */
:root {
  --primary-color: #00ffff;
  --secondary-color: #ff00ff;
  --accent-color: #ffff00;
  --success-color: #00ff00;
  --danger-color: #ff0040;
  --warning-color: #ff8000;
  --info-color: #0080ff;
  
  --bg-primary: #0a0a0a;
  --bg-secondary: #1a1a2e;
  --bg-tertiary: #16213e;
  --text-primary: #ffffff;
  --text-secondary: #b0b0b0;
  --text-accent: #00ffff;
  --border-color: #333366;
  --shadow: 0 0 20px rgba(0, 255, 255, 0.3);
  --glow: 0 0 30px rgba(0, 255, 255, 0.5);
}

.theme-light {
  --bg-primary: #f0f0f0;
  --bg-secondary: #ffffff;
  --bg-tertiary: #e8e8e8;
  --text-primary: #1a1a1a;
  --text-secondary: #666666;
  --text-accent: #0066cc;
  --border-color: #cccccc;
  --shadow: 0 0 20px rgba(0, 102, 204, 0.2);
  --glow: 0 0 30px rgba(0, 102, 204, 0.3);
}

/* Reset y base */
* {
  box-sizing: border-box;
}

/* Contenedor principal con efectos futuristas */
.back-umm-unificado-container {
  min-height: 100vh;
  background: linear-gradient(135deg, var(--bg-primary) 0%, var(--bg-secondary) 50%, var(--bg-tertiary) 100%);
  color: var(--text-primary);
  position: relative;
  overflow-x: hidden;
  font-family: 'Courier New', monospace;
}

/* Efectos de partículas de fondo */
.particles-container {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  pointer-events: none;
  z-index: 1;
}

.particle {
  position: absolute;
  background: var(--primary-color);
  border-radius: 50%;
  opacity: 0.6;
  animation: float infinite linear;
  box-shadow: 0 0 10px var(--primary-color);
}

@keyframes float {
  0% {
    transform: translateY(100vh) rotate(0deg);
    opacity: 0;
  }
  10% {
    opacity: 0.6;
  }
  90% {
    opacity: 0.6;
  }
  100% {
    transform: translateY(-100px) rotate(360deg);
    opacity: 0;
  }
}

/* Ondas de energía */
.energy-waves {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  pointer-events: none;
  z-index: 2;
}

.wave {
  position: absolute;
  width: 200%;
  height: 200%;
  border: 2px solid var(--primary-color);
  border-radius: 50%;
  opacity: 0.1;
  animation: wave-expand 8s infinite;
}

.wave-1 {
  top: -50%;
  left: -50%;
  animation-delay: 0s;
}

.wave-2 {
  top: -50%;
  left: -50%;
  animation-delay: 2.5s;
}

.wave-3 {
  top: -50%;
  left: -50%;
  animation-delay: 5s;
}

@keyframes wave-expand {
  0% {
    transform: scale(0);
    opacity: 0.3;
  }
  50% {
    opacity: 0.1;
  }
  100% {
    transform: scale(1);
    opacity: 0;
  }
}

/* Panel holográfico */
.holographic-panel {
  position: relative;
  background: linear-gradient(45deg, rgba(0, 255, 255, 0.1), rgba(255, 0, 255, 0.1));
  border: 1px solid var(--primary-color);
  border-radius: 15px;
  margin: 2rem;
  padding: 2rem;
  box-shadow: var(--glow);
  z-index: 10;
  backdrop-filter: blur(10px);
}

.panel-glow {
  position: absolute;
  top: -2px;
  left: -2px;
  right: -2px;
  bottom: -2px;
  background: linear-gradient(45deg, var(--primary-color), var(--secondary-color), var(--accent-color));
  border-radius: 15px;
  z-index: -1;
  animation: glow-pulse 3s infinite;
}

@keyframes glow-pulse {
  0%, 100% { opacity: 0.5; }
  50% { opacity: 0.8; }
}

.main-title {
  text-align: center;
  margin-bottom: 1rem;
  position: relative;
}

.title-text {
  font-size: 2.5rem;
  font-weight: bold;
  background: linear-gradient(45deg, var(--primary-color), var(--secondary-color), var(--accent-color));
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
  text-shadow: 0 0 30px var(--primary-color);
  animation: title-glow 2s infinite alternate;
}

@keyframes title-glow {
  0% { text-shadow: 0 0 30px var(--primary-color); }
  100% { text-shadow: 0 0 50px var(--primary-color), 0 0 70px var(--secondary-color); }
}

.title-underline {
  width: 200px;
  height: 3px;
  background: linear-gradient(90deg, var(--primary-color), var(--secondary-color));
  margin: 0.5rem auto;
  border-radius: 2px;
  animation: underline-pulse 2s infinite;
}

@keyframes underline-pulse {
  0%, 100% { transform: scaleX(1); }
  50% { transform: scaleX(1.2); }
}

.status-indicators {
  display: flex;
  justify-content: center;
  gap: 2rem;
  margin-top: 1rem;
}

.status-item {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  font-size: 0.9rem;
}

.status-dot {
  width: 12px;
  height: 12px;
  border-radius: 50%;
  animation: status-pulse 2s infinite;
}

.status-dot.active {
  background: var(--success-color);
  box-shadow: 0 0 10px var(--success-color);
}

.status-dot.processing {
  background: var(--warning-color);
  box-shadow: 0 0 10px var(--warning-color);
}

@keyframes status-pulse {
  0%, 100% { opacity: 1; }
  50% { opacity: 0.5; }
}

/* Navegación por pestañas futurista */
.tabs-container {
  background: linear-gradient(90deg, rgba(0, 255, 255, 0.1), rgba(255, 0, 255, 0.1));
  border-bottom: 2px solid var(--primary-color);
  padding: 0 2rem;
  position: relative;
  z-index: 10;
  backdrop-filter: blur(10px);
}

.tabs-wrapper {
  display: flex;
  gap: 0;
  overflow-x: auto;
  padding: 1rem 0;
}

.tab-button {
  background: linear-gradient(45deg, rgba(0, 255, 255, 0.1), rgba(255, 0, 255, 0.1));
  border: 1px solid var(--border-color);
  padding: 1rem 1.5rem;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 0.5rem;
  color: var(--text-secondary);
  border-bottom: 3px solid transparent;
  transition: all 0.4s cubic-bezier(0.4, 0, 0.2, 1);
  white-space: nowrap;
  position: relative;
  font-size: 0.95rem;
  font-weight: 500;
  overflow: hidden;
  backdrop-filter: blur(10px);
}

.tab-button::before {
  content: '';
  position: absolute;
  top: 0;
  left: -100%;
  width: 100%;
  height: 100%;
  background: linear-gradient(90deg, transparent, rgba(255, 255, 255, 0.2), transparent);
  transition: left 0.5s;
}

.tab-button:hover::before {
  left: 100%;
}

.tab-button:hover {
  color: var(--primary-color);
  background: linear-gradient(45deg, rgba(0, 255, 255, 0.2), rgba(255, 0, 255, 0.2));
  transform: translateY(-2px);
  box-shadow: var(--shadow);
}

.tab-button.active {
  color: var(--primary-color);
  border-bottom-color: var(--primary-color);
  background: linear-gradient(45deg, rgba(0, 255, 255, 0.3), rgba(255, 0, 255, 0.3));
  box-shadow: var(--glow);
}

.tab-icon-container {
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
}

.tab-icon-container i {
  position: relative;
  z-index: 2;
  transition: all 0.3s ease;
}

.icon-glow {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 30px;
  height: 30px;
  background: var(--primary-color);
  border-radius: 50%;
  opacity: 0;
  filter: blur(10px);
  transition: all 0.3s ease;
}

.tab-button:hover .icon-glow {
  opacity: 0.3;
  animation: icon-pulse 1s infinite;
}

@keyframes icon-pulse {
  0%, 100% { transform: translate(-50%, -50%) scale(1); }
  50% { transform: translate(-50%, -50%) scale(1.2); }
}

.tab-badge {
  background: linear-gradient(45deg, var(--success-color), var(--accent-color));
  color: var(--bg-primary);
  font-size: 0.7rem;
  padding: 0.2rem 0.5rem;
  border-radius: 10px;
  margin-left: 0.25rem;
  position: relative;
  overflow: hidden;
}

.badge-pulse {
  position: absolute;
  top: 0;
  left: -100%;
  width: 100%;
  height: 100%;
  background: linear-gradient(90deg, transparent, rgba(255, 255, 255, 0.5), transparent);
  animation: badge-shine 2s infinite;
}

@keyframes badge-shine {
  0% { left: -100%; }
  100% { left: 100%; }
}

.tab-ripple {
  position: absolute;
  top: 50%;
  left: 50%;
  width: 0;
  height: 0;
  background: rgba(255, 255, 255, 0.3);
  border-radius: 50%;
  transform: translate(-50%, -50%);
  transition: all 0.6s ease;
}

.tab-button:active .tab-ripple {
  width: 200px;
  height: 200px;
  opacity: 0;
}

/* Contenido de las pestañas */
.tab-content {
  padding: 2rem;
  min-height: calc(100vh - 300px);
  position: relative;
  z-index: 5;
}

.tab-panel {
  animation: panel-slide-in 0.6s cubic-bezier(0.4, 0, 0.2, 1);
}

@keyframes panel-slide-in {
  from { 
    opacity: 0; 
    transform: translateY(30px) scale(0.95); 
  }
  to { 
    opacity: 1; 
    transform: translateY(0) scale(1); 
  }
}

.panel-content {
  background: linear-gradient(135deg, rgba(0, 255, 255, 0.05), rgba(255, 0, 255, 0.05));
  border-radius: 20px;
  padding: 2rem;
  box-shadow: var(--shadow);
  border: 1px solid var(--border-color);
  backdrop-filter: blur(15px);
  position: relative;
  overflow: hidden;
}

.panel-content::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: linear-gradient(45deg, transparent 30%, rgba(255, 255, 255, 0.1) 50%, transparent 70%);
  transform: translateX(-100%);
  animation: panel-shine 3s infinite;
}

@keyframes panel-shine {
  0% { transform: translateX(-100%); }
  100% { transform: translateX(100%); }
}

.panel-header {
  text-align: center;
  margin-bottom: 2rem;
  position: relative;
}

.header-icon {
  position: relative;
  display: inline-block;
  margin-bottom: 1rem;
}

.header-icon i {
  font-size: 4rem;
  color: var(--primary-color);
  animation: icon-float 3s ease-in-out infinite;
}

@keyframes icon-float {
  0%, 100% { transform: translateY(0px); }
  50% { transform: translateY(-10px); }
}

.icon-aura {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 100px;
  height: 100px;
  background: radial-gradient(circle, var(--primary-color) 0%, transparent 70%);
  opacity: 0.3;
  border-radius: 50%;
  animation: aura-pulse 2s infinite;
}

@keyframes aura-pulse {
  0%, 100% { transform: translate(-50%, -50%) scale(1); opacity: 0.3; }
  50% { transform: translate(-50%, -50%) scale(1.2); opacity: 0.1; }
}

.panel-title {
  font-size: 2rem;
  margin: 0 0 1rem 0;
  background: linear-gradient(45deg, var(--primary-color), var(--secondary-color));
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
  text-shadow: 0 0 20px var(--primary-color);
}

.title-accent {
  width: 100px;
  height: 3px;
  background: linear-gradient(90deg, var(--primary-color), var(--secondary-color));
  margin: 0 auto;
  border-radius: 2px;
  animation: accent-glow 2s infinite alternate;
}

@keyframes accent-glow {
  0% { box-shadow: 0 0 10px var(--primary-color); }
  100% { box-shadow: 0 0 20px var(--primary-color), 0 0 30px var(--secondary-color); }
}

.panel-description {
  text-align: center;
  margin-bottom: 2rem;
}

.panel-description p {
  font-size: 1.1rem;
  color: var(--text-secondary);
  margin: 0 0 1rem 0;
}

.description-line {
  width: 200px;
  height: 1px;
  background: linear-gradient(90deg, transparent, var(--primary-color), transparent);
  margin: 0 auto;
  animation: line-glow 2s infinite;
}

@keyframes line-glow {
  0%, 100% { opacity: 0.5; }
  50% { opacity: 1; }
}

/* Grid de características futurista */
.feature-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 1.5rem;
  max-width: 1200px;
  margin: 0 auto;
}

.feature-card {
  background: linear-gradient(135deg, rgba(0, 255, 255, 0.1), rgba(255, 0, 255, 0.1));
  border: 1px solid var(--border-color);
  border-radius: 15px;
  padding: 1.5rem;
  transition: all 0.4s cubic-bezier(0.4, 0, 0.2, 1);
  position: relative;
  overflow: hidden;
  backdrop-filter: blur(10px);
  cursor: pointer;
}

.feature-card::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: linear-gradient(45deg, transparent 30%, rgba(255, 255, 255, 0.1) 50%, transparent 70%);
  transform: translateX(-100%);
  transition: transform 0.6s;
}

.feature-card:hover::before {
  transform: translateX(100%);
}

.card-glow {
  position: absolute;
  top: -2px;
  left: -2px;
  right: -2px;
  bottom: -2px;
  background: linear-gradient(45deg, var(--primary-color), var(--secondary-color), var(--accent-color));
  border-radius: 15px;
  z-index: -1;
  opacity: 0;
  transition: opacity 0.3s ease;
}

.feature-card:hover .card-glow {
  opacity: 0.5;
  animation: card-glow-pulse 1s infinite;
}

@keyframes card-glow-pulse {
  0%, 100% { opacity: 0.5; }
  50% { opacity: 0.8; }
}

.feature-card:hover {
  transform: translateY(-5px) scale(1.02);
  box-shadow: var(--glow);
  border-color: var(--primary-color);
}

.card-content {
  position: relative;
  z-index: 2;
}

.feature-icon {
  position: relative;
  display: inline-block;
  margin-bottom: 1rem;
}

.feature-icon i {
  font-size: 2rem;
  color: var(--primary-color);
  transition: all 0.3s ease;
}

.icon-pulse {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 40px;
  height: 40px;
  background: var(--primary-color);
  border-radius: 50%;
  opacity: 0;
  filter: blur(8px);
  transition: all 0.3s ease;
}

.feature-card:hover .icon-pulse {
  opacity: 0.3;
  animation: icon-pulse-effect 1s infinite;
}

@keyframes icon-pulse-effect {
  0%, 100% { transform: translate(-50%, -50%) scale(1); }
  50% { transform: translate(-50%, -50%) scale(1.3); }
}

.feature-card h4 {
  margin: 0 0 0.5rem 0;
  color: var(--text-primary);
  font-size: 1.2rem;
  font-weight: 600;
}

.feature-card p {
  margin: 0;
  color: var(--text-secondary);
  font-size: 0.9rem;
  line-height: 1.4;
}

.card-ripple {
  position: absolute;
  top: 50%;
  left: 50%;
  width: 0;
  height: 0;
  background: rgba(0, 255, 255, 0.3);
  border-radius: 50%;
  transform: translate(-50%, -50%);
  transition: all 0.6s ease;
}

.feature-card.clickable:active .card-ripple {
  width: 300px;
  height: 300px;
  opacity: 0;
}

/* Controles flotantes */
.floating-controls {
  position: fixed;
  top: 50%;
  right: 2rem;
  transform: translateY(-50%);
  display: flex;
  flex-direction: column;
  gap: 1rem;
  z-index: 100;
}

.control-btn {
  width: 50px;
  height: 50px;
  border: none;
  border-radius: 50%;
  background: linear-gradient(45deg, var(--primary-color), var(--secondary-color));
  color: var(--bg-primary);
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 1.2rem;
  transition: all 0.3s ease;
  box-shadow: var(--shadow);
  position: relative;
  overflow: hidden;
}

.control-btn::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: linear-gradient(45deg, rgba(255, 255, 255, 0.2), transparent);
  transform: translateX(-100%);
  transition: transform 0.3s;
}

.control-btn:hover::before {
  transform: translateX(100%);
}

.control-btn:hover {
  transform: scale(1.1);
  box-shadow: var(--glow);
}

.control-btn:disabled {
  opacity: 0.5;
  cursor: not-allowed;
  transform: none;
}

/* Responsive */
@media (max-width: 768px) {
  .tabs-container {
    padding: 0 1rem;
  }
  
  .tab-content {
    padding: 1rem;
  }
  
  .panel-content {
    padding: 1.5rem;
  }
  
  .feature-grid {
    grid-template-columns: 1fr;
  }
  
  .floating-controls {
    right: 1rem;
    flex-direction: row;
    top: auto;
    bottom: 2rem;
    transform: none;
  }
  
  .main-title .title-text {
    font-size: 2rem;
  }
  
  .panel-title {
    font-size: 1.5rem;
  }
}

@media (max-width: 480px) {
  .holographic-panel {
    margin: 1rem;
    padding: 1rem;
  }
  
  .main-title .title-text {
    font-size: 1.5rem;
  }
  
  .status-indicators {
    flex-direction: column;
    gap: 0.5rem;
  }
}
</style>