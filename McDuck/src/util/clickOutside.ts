// src/directives/clickOutside.ts
import type { ObjectDirective, DirectiveBinding } from 'vue'

interface HTMLElementWithClickOutside extends HTMLElement {
  clickOutsideEvent?: (event: MouseEvent) => void
}

export const clickOutside: ObjectDirective<HTMLElementWithClickOutside, () => void> = {
  beforeMount(el: HTMLElementWithClickOutside, binding: DirectiveBinding<() => void>) {
    el.clickOutsideEvent = function (event: MouseEvent) {
      if (!(el === event.target || el.contains(event.target as Node))) {
        binding.value()
      }
    }
    document.addEventListener('click', el.clickOutsideEvent)
  },
  unmounted(el: HTMLElementWithClickOutside) {
    if (el.clickOutsideEvent) {
      document.removeEventListener('click', el.clickOutsideEvent)
    }
  }
}
